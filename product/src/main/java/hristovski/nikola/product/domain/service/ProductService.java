package hristovski.nikola.product.domain.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.common.shared.domain.model.product.Product;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.exception.ProductNotFoundException;
import hristovski.nikola.product.domain.persistance.entity.ProductEntity;

import java.util.List;
import java.util.Set;

public interface ProductService {

    PersonalizedProduct getById(ProductId productId, ApplicationUserId userId) throws ProductNotFoundException, RestRequestException;

    ProductEntity getById(ProductId productId) throws ProductNotFoundException;

    List<PersonalizedProduct> getProducts(int page, int size, ApplicationUserId userId) throws RestRequestException;

    List<PersonalizedProduct> getProductsSortedByRating(int page, int size, ApplicationUserId userId) throws RestRequestException;

    List<PersonalizedProduct> getProductsInCategory(int page, int size,
                                                    CategoryId categoryId, ApplicationUserId userId)
            throws CategoryNotFoundException, RestRequestException;

    void adjustProductRatingStatistics(ProductId productId, Integer rating)
            throws ProductNotFoundException;

    void adjustProductRatingStatistics(ProductId productId, Integer oldRating, Integer newRating)
            throws ProductNotFoundException;

    ProductEntity addProduct(ImageURL imageLocation, ProductInformation information, Money price,
                             Set<Category> categories, Quantity stock);

    ProductEntity editProduct(Product product)
            throws ProductNotFoundException;

    void deleteProduct(ProductId productId) throws ProductNotFoundException;

//    void removeProductsFromCategory(CateogoryId );

    List<Product> search(String query) throws RestRequestException;

    int maxPages(Integer size);

    int maxPagesInCategory(Integer size, CategoryId categoryId) throws CategoryNotFoundException;


    ProductEntity refreshProduct(ProductId productId) throws ProductNotFoundException;
}
