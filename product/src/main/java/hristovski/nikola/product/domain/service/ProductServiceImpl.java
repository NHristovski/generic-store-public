package hristovski.nikola.product.domain.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.common.shared.domain.model.product.Product;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.message.domain.event.ProductCreatedEvent;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.exception.ProductNotFoundException;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import hristovski.nikola.product.domain.remote.service.InventoryService;
import hristovski.nikola.product.domain.remote.service.RatingService;
import hristovski.nikola.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingService ratingService;
    private final InventoryService inventoryService;

    private final ConversionService conversionService;
    private final CategoryService categoryService;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public PersonalizedProduct getById(ProductId productId, ApplicationUserId userId)
            throws ProductNotFoundException, RestRequestException {
        ProductEntity productEntity = getProductEntity(productId);

        Map<ProductId, RatingResponseElement> productRatings = ratingService.findRatings(
                List.of(productEntity.getId()),
                userId
        );

        Map<ProductId, StockResponseElement> productStocks = inventoryService.getProductStocks(
                List.of(productEntity.getId())
        );

        return Stream.of(productEntity)
                .map(product -> personalizeProduct(product, productRatings))
                .map(product -> adjustStock(product, productStocks))
                .collect(Collectors.toList()).get(0);
    }

    private ProductEntity getProductEntity(ProductId productId) throws ProductNotFoundException {
        return productRepository.findByDeletedFalseAndIdEquals(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
    }

    private PersonalizedProduct personalizeProduct(ProductEntity productEntity,
                                                   Map<ProductId, RatingResponseElement> productRatings) {
        PersonalizedProduct personalizedProduct
                = conversionService.convert(productEntity, PersonalizedProduct.class);

        Objects.requireNonNull(personalizedProduct, "PersonalizedProduct must not be null!");

        log.info("Getting rating response element for product {} from map {}", personalizedProduct.getProductId()
                , productRatings);
        RatingResponseElement ratingResponseElement = productRatings.get(personalizedProduct.getProductId());
        log.info("The response element is {}", ratingResponseElement);

        if (ratingResponseElement.hasError()) {
            personalizedProduct.setCurrentUserRating(0);
        } else {
            personalizedProduct.setCurrentUserRating(ratingResponseElement.getRating());
        }

        return personalizedProduct;
////        Integer rating = 0;
////        try {
////            rating = ratingService.getCurrentRating(productEntity.getId(), userId);
////        } catch (RestRequestException exception) {
////            log.error(
////                    "Failed to obtain the current rating for product {} from user {}"
////                    , productEntity.getId().getId(), userId.getId(), exception
////            );
////        }
//
//        personalizedProduct.setCurrentUserRating(rating);
//
//        return personalizedProduct;
    }

    @Override
    public ProductEntity getById(ProductId productId) throws ProductNotFoundException {
        return productRepository.findByDeletedFalseAndIdEquals(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
    }

    @Override
    public List<PersonalizedProduct> getProducts(int page, int size, ApplicationUserId userId)
            throws RestRequestException {

        List<ProductEntity> products = productRepository.
                findAllByDeletedFalseOrderByCreatedOnDesc(PageRequest.of(page, size))
                .getContent();

        List<ProductId> productIds = products.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        Map<ProductId, StockResponseElement> productStocks = inventoryService.getProductStocks(
                productIds
        );

        Map<ProductId, RatingResponseElement> productRatings = ratingService.findRatings(
                productIds,
                userId
        );

        return products.stream()
                .map(product -> personalizeProduct(product, productRatings))
                .map(product -> adjustStock(product, productStocks))
                .collect(Collectors.toList());
    }

    private PersonalizedProduct adjustStock(PersonalizedProduct product, Map<ProductId, StockResponseElement> productStocks) {
        StockResponseElement stockResponseElement = productStocks.get(product.getProductId());

        if (stockResponseElement.hasError()) {
            product.setStock(new Quantity(0L));
        } else {
            product.setStock(stockResponseElement.getQuantity());
        }

        return product;
    }

    private Product adjustStock(ProductEntity product, Map<ProductId, StockResponseElement> productStocks) {
        StockResponseElement stockResponseElement = productStocks.get(product.getId());

        if (stockResponseElement.hasError()) {
            return product.toProduct();
        }

        Product newProduct = product.toProduct();
        newProduct.setStock(stockResponseElement.getQuantity());
        return newProduct;
    }

    @Override
    public List<PersonalizedProduct> getProductsSortedByRating(int page, int size, ApplicationUserId userId)
            throws RestRequestException {
        List<ProductEntity> products = productRepository
                .findAllByDeletedFalseOrderByRatingStatisticsAverageRatingDesc(PageRequest.of(page, size))
                .getContent();

        List<ProductId> productIds = products.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        Map<ProductId, StockResponseElement> productStocks = inventoryService.getProductStocks(
                productIds
        );

        Map<ProductId, RatingResponseElement> productRatings = ratingService.findRatings(
                productIds,
                userId
        );

        return products.stream()
                .map(product -> personalizeProduct(product, productRatings))
                .map(product -> adjustStock(product, productStocks))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalizedProduct> getProductsInCategory(int page, int size,
                                                           CategoryId categoryId, ApplicationUserId userId)
            throws CategoryNotFoundException, RestRequestException {

        CategoryEntity categoryEntity = categoryService.getById(categoryId);

        List<ProductEntity> products = productRepository
                .queryProductsByDeletedFalseAndCategoriesIsContainingOrderByCreatedOnDesc(
                        categoryEntity, PageRequest.of(page, size)
                )
                .getContent();

        List<ProductId> productIds = products.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        Map<ProductId, StockResponseElement> productStocks = inventoryService.getProductStocks(
                productIds
        );

        Map<ProductId, RatingResponseElement> productRatings = ratingService.findRatings(
                productIds,
                userId
        );

        return products.stream()
                .map(product -> personalizeProduct(product, productRatings))
                .map(product -> adjustStock(product, productStocks))
                .collect(Collectors.toList());
    }

    @Override
    public void adjustProductRatingStatistics(ProductId productId, Integer rating)
            throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        productEntity.adjustRatingStatistics(rating);

        productRepository.save(productEntity);
    }

    @Override
    public void adjustProductRatingStatistics(ProductId productId, Integer oldRating, Integer newRating)
            throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        productEntity.adjustRatingStatistics(oldRating, newRating);

        productRepository.save(productEntity);
    }

    @Override
    public ProductEntity addProduct(ImageURL imageLocation, ProductInformation information, Money price,
                                    Set<Category> categories, Quantity stock) {
        ProductEntity productEntity = new ProductEntity(imageLocation, information, price);

        ProductEntity savedProductEntity = setCategoriesAndSaveProduct(categories, productEntity);

        domainEventPublisher.publish(
                new ProductCreatedEvent(savedProductEntity.getId(), stock)
        );

        return savedProductEntity;
    }


    @Override
    public ProductEntity editProduct(Product product) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findByDeletedFalseAndIdEquals(product.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(product.getProductId().getId()));

        productEntity.setVersion(product.getVersion());
        productEntity.setPrice(product.getPrice());
        productEntity.setInformation(product.getInformation());
        productEntity.setImageLocation(product.getImageLocation());
//        ProductEntity productEntity = new ProductEntity(product);

        return setCategoriesAndSaveProduct(product.getCategories(), productEntity);
    }

    private ProductEntity setCategoriesAndSaveProduct(Set<Category> categories, ProductEntity productEntity) {
        productEntity.setCategories(
                categories.stream()
                        .map(category -> {
                            try {
                                return categoryService.getById(category.getCategoryId());
                            } catch (CategoryNotFoundException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet())
        );


        return productRepository.save(productEntity);
    }


    @Override
    public void deleteProduct(ProductId productId) throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        productEntity.delete();
        productRepository.save(productEntity);
    }

    @Override
    public List<Product> search(String query) throws RestRequestException {
        List<ProductEntity> products = productRepository
                .findAllByDeletedFalseAndIdEqualsOrDeletedFalseAndInformationTitleContainsIgnoreCaseOrderByCreatedOnDesc(
                        new ProductId(query), query
                );

        List<ProductId> productIds = products.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        Map<ProductId, StockResponseElement> productStocks = inventoryService.getProductStocks(
                productIds
        );

        return products.stream()
                .map(p -> adjustStock(p, productStocks))
                .collect(Collectors.toList());
    }

    @Override
    public int maxPages(Integer size) {
        long count = productRepository.countAllByDeletedFalse();

        double ceil = Math.ceil((count / (size * 1.0)));

        return (int) ceil;
    }

    @Override
    public int maxPagesInCategory(Integer size, CategoryId categoryId) throws CategoryNotFoundException {
        long count = productRepository
                .countProductsByDeletedFalseAndCategoriesIsContaining(
                        categoryService.getById(categoryId)
                );

        double ceil = Math.ceil((count / (size * 1.0)));

        return (int) ceil;
    }

    @Override
    public ProductEntity refreshProduct(ProductId productId) throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        productEntity.refresh();
        return productRepository.save(productEntity);
    }

//    @Override
//    public ProductEntity editProduct(Long id, AddProductRequest productRequest) throws ProductNotFoundException {
//        ProductEntity productEntity = productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
//
//        productEntity.setStock(productRequest.getStock());
//        productEntity.setDescription(productRequest.getDescription());
//        productEntity.setImageLocation(productRequest.getImageLocation());
//        productEntity.setTitle(productRequest.getTitle());
//        productEntity.setPrice(productRequest.getPrice());
//
//        List<CategoryEntity> categories = productRequest.getCategoryNames()
//                .stream()
//                .map(categoryRepository::getByCategoryName)
//                .collect(Collectors.toList());
//
//
//        productEntity.setCategories(categories);
//
//        return productRepository.save(productEntity);
//    }
//
//    @Override
//    public void removeProductsFromCategory(CategoryEntity categoryEntity) {
//        Iterable<ProductEntity> all = productRepository.findAll();
//
//        for (ProductEntity next : all) {
//            log.info("product is: {} categoris are: {}", next.getId(), next.getCategories());
//
//            List<CategoryEntity> categories1 = next.getCategories();
//            List<CategoryEntity> newCategories = categories1.stream()
//                    .filter(cat -> !cat.getId().equals(categoryEntity.getId()))
//                    .collect(Collectors.toList());
//
//            if (categories1.size() != newCategories.size()) {
//                log.info("categories1 is not the same as newCategories");
//                next.setCategories(newCategories);
//                productRepository.save(next);
//                log.info("$$$ Now the product should now have the category");
//            }
//        }
//    }
//


}

