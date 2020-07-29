package hristovski.nikola.product.application.converter;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalizedProductConverter implements Converter<ProductEntity, PersonalizedProduct> {

    @Override
    public PersonalizedProduct convert(ProductEntity productEntity) {
        return new PersonalizedProduct(
                productEntity.getId(),
                productEntity.getVersion(),
                productEntity.getImageLocation(),
                productEntity.getInformation(),
                productEntity.getCreatedOn(),
                productEntity.getPrice(),
                null,
                productEntity.getRatingStatistics(),
                productEntity.getCategories()
                        .stream()
                        .map(this::mapCategoryEntityToCategory)
                        .collect(Collectors.toSet()),
                null
        );
    }

    public Category mapCategoryEntityToCategory(CategoryEntity categoryEntity) {
        return new Category(
                categoryEntity.id(),
                categoryEntity.getVersion(),
                categoryEntity.getCategoryName()
        );
    }

}
