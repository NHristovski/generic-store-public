package hristovski.nikola.product.application.converter;

import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.product.domain.persistance.entity.CategoryEntity;
import hristovski.nikola.product.domain.persistance.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
                        .map(CategoryEntity::toCategory)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()),
                null
        );
    }
}
