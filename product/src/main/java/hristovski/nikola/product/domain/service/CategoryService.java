package hristovski.nikola.product.domain.service;

import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.persistance.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity addCategory(Name categoryName);

    CategoryEntity editCategory(CategoryId categoryId, Name newName) throws CategoryNotFoundException;

    void deleteCategory(CategoryId categoryId) throws CategoryNotFoundException;

    //CategoryEntity getByCategoryName(String categoryName) throws CategoryNotFoundException;

    List<CategoryEntity> getAll();

    CategoryEntity getById(CategoryId categoryId) throws CategoryNotFoundException;
}
