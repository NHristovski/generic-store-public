package hristovski.nikola.product.application.converter;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryConverter implements Converter<CategoryEntity, Category> {
    @Override
    public Category convert(CategoryEntity categoryEntity) {
        return new Category(
                categoryEntity.id(),
                categoryEntity.getVersion(),
                categoryEntity.getCategoryName()
        );
    }
}
