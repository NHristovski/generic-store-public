package hristovski.nikola.product.application.converter;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.product.Product;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import hristovski.nikola.product.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductEntityConverter implements Converter<Product, ProductEntity> {

    private final CategoryService categoryService;

    @Override
    public ProductEntity convert(Product product) {
        ProductEntity productEntity = new ProductEntity(product);

        productEntity.setCategories(mapCategoriesToCategoryEntities(product.getCategories()));

        return productEntity;
    }

    Set<CategoryEntity> mapCategoriesToCategoryEntities(Set<Category> categories) {
        return categories.stream()
                .map(category -> {
                    try {
                        return categoryService.getById(category.getCategoryId());
                    } catch (CategoryNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
