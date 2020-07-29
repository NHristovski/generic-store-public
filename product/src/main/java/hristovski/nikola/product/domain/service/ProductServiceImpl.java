package hristovski.nikola.product.domain.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.common.shared.domain.model.product.Product;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.exception.ProductNotFoundException;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import hristovski.nikola.product.domain.remote.service.RatingService;
import hristovski.nikola.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingService ratingService;
    private final ConversionService conversionService;
    private final CategoryService categoryService;

    @Override
    public PersonalizedProduct getById(ProductId productId, ApplicationUserId userId) throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        return personalizeProduct(productEntity, userId);
    }

    private ProductEntity getProductEntity(ProductId productId) throws ProductNotFoundException {
        return productRepository.findByDeletedFalseAndIdEquals(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
    }

    private PersonalizedProduct personalizeProduct(ProductEntity productEntity, ApplicationUserId userId) {
        PersonalizedProduct personalizedProduct
                = conversionService.convert(productEntity, PersonalizedProduct.class);

        Objects.requireNonNull(personalizedProduct, "PersonalizedProduct must not be null!");

        Integer rating = 0;
        try {
            rating = ratingService.getCurrentRating(productEntity.getId(), userId);
        } catch (RestRequestException exception) {
            log.error(
                    "Failed to obtain the current rating for product {} from user {}"
                    , productEntity.getId().getId(), userId.getId(), exception
            );
        }

        personalizedProduct.setCurrentUserRating(rating);

        return personalizedProduct;
    }

    @Override
    public ProductEntity getById(ProductId productId) throws ProductNotFoundException {
        return productRepository.findByDeletedFalseAndIdEquals(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
    }

    @Override
    public List<PersonalizedProduct> getProducts(int page, int size, ApplicationUserId userId) {
        return productRepository.findAllByDeletedFalseOrderByCreatedOnDesc(PageRequest.of(page, size))
                .getContent().stream()
                .map(product -> personalizeProduct(product, userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalizedProduct> getProductsSortedByRating(int page, int size, ApplicationUserId userId) {
        return productRepository
                .findAllByDeletedFalseOrderByRatingStatisticsAverageRatingDesc(PageRequest.of(page, size))
                .getContent().stream()
                .map(product -> personalizeProduct(product, userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalizedProduct> getProductsInCategory(int page, int size,
                                                           CategoryId categoryId, ApplicationUserId userId)
            throws CategoryNotFoundException {

        CategoryEntity categoryEntity = categoryService.getById(categoryId);

        return productRepository
                .queryProductsByDeletedFalseAndCategoriesIsContainingOrderByCreatedOnDesc(categoryEntity, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(product -> personalizeProduct(product, userId))
                .collect(Collectors.toList());

    }

    @Override
    public void adjustProductRatingStatistics(ProductId productId, Integer rating)
            throws ProductNotFoundException {
        ProductEntity productEntity = getProductEntity(productId);

        productEntity.adjustRatingStatistics(rating);
//        RatingStatistics newRatingStatistics = productEntity.getRatingStatistics().addRating(rating);
//        productEntity.setRatingStatistics(newRatingStatistics);

        productRepository.save(productEntity);
    }

    @Override
    public ProductEntity addProduct(ImageURL imageLocation, ProductInformation information, Money price,
                                    Quantity stock, Set<Category> categories) {
        ProductEntity productEntity = new ProductEntity(imageLocation, information, price);
        // TODO WHAT TO DO WHIT THE STOCK? SEND EVENT

        return setCategoriesAndSaveProduct(categories, productEntity);
    }


    @Override
    public ProductEntity editProduct(Product product) throws ProductNotFoundException {
        ProductEntity productEntity = new ProductEntity(product);

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
    public List<Product> search(String query) {
        return productRepository
                .findAllByDeletedFalseAndIdEqualsOrInformationTitleContainsOrderByCreatedOnDesc(
                        new ProductId(query), query
                ).stream()
                .map(ProductEntity::toProduct)
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

