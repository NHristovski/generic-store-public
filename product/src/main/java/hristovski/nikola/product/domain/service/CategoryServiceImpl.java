package hristovski.nikola.product.domain.service;


import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity addCategory(Name categoryName) {
        return categoryRepository.saveAndFlush(new CategoryEntity(categoryName));
    }

    @Override
    public CategoryEntity editCategory(CategoryId categoryId, Name newName) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = findCategoryEntity(categoryId);

        categoryEntity.setCategoryName(newName);

        return categoryRepository.saveAndFlush(categoryEntity);
    }

    @Override
    public void deleteCategory(CategoryId categoryId) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = findCategoryEntity(categoryId);

        categoryEntity.delete();

        categoryRepository.saveAndFlush(categoryEntity);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAllByDeletedFalse();
    }

    @Override
    public CategoryEntity getById(CategoryId categoryId) throws CategoryNotFoundException {
        return findCategoryEntity(categoryId);
    }

    private CategoryEntity findCategoryEntity(CategoryId categoryId) throws CategoryNotFoundException {
        return categoryRepository.findByDeletedFalseAndIdEquals(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId.getId()));
    }

//    private final ProductService productService;

//    @Override
//    public CategoryEntity addCategory(AddCategoryRequest request) {
//
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setCategoryName(request.getCategoryName());
//        categoryEntity.setId(null);
//        categoryEntity.setProductEntities(new ArrayList<>());
//
//        return categoryRepository.save(categoryEntity);
//    }
//
//    @Override
//    public CategoryEntity editCategory(CategoryEntity categoryEntity) {
//        return categoryRepository.save(categoryEntity);
//    }
//
//    @Override
//    public void deleteCategory(Long categoryId) {
//
//        CategoryEntity one = categoryRepository.getOne(categoryId);
//
//        productService.removeProductsFromCategory(one);
//
//        categoryRepository.deleteById(categoryId);
//    }
//
//    @Override
//    public CategoryEntity getByCategoryName(String categoryName) throws CategoryNotFoundException {
//        try {
//            return categoryRepository.getByCategoryName(categoryName);
//        }catch (Exception ex){
//            log.error("Failed to get category with name {}",categoryName,ex);
//        }
//
//        throw new CategoryNotFoundException(categoryName);
//    }
//
//    @Override
//    public List<CategoryEntity> getAll() {
//        return categoryRepository.findAll();
//    }
}
