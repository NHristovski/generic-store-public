package hristovski.nikola.product.application.port.rest;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.generic_store.message.domain.rest.category.request.AddCategoryRequest;
import hristovski.nikola.generic_store.message.domain.rest.category.response.CategorySavedResponse;
import hristovski.nikola.generic_store.message.domain.rest.category.response.GetAllCategoriesResponse;
import hristovski.nikola.generic_store.message.domain.rest.common.response.DeletionSuccessfulResponse;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final ConversionService conversionService;

    // TODO MOVE THE CONVERSION TO AN SERVICE
    @GetMapping
    public ResponseEntity<GetAllCategoriesResponse> getAllCategories() {
        return ResponseEntity.ok(
                GetAllCategoriesResponse.builder()
                        .categories(
                                categoryService.getAll()
                                        .stream()
                                        .map(
                                                categoryEntity ->
                                                        conversionService.convert(categoryEntity, Category.class)
                                        )
                                        .collect(Collectors.toList())
                        ).build()
        );
    }

    @PostMapping("/add_category")
    public ResponseEntity<CategorySavedResponse> addCategory(
            @Valid @RequestBody AddCategoryRequest addCategoryRequest) {

        return ResponseEntity.ok(
                CategorySavedResponse.builder()
                        .category(
                                categoryService
                                        .addCategory(addCategoryRequest.getCategoryName())
                                        .toCategory()
                        )
                        .build()
        );
    }

    @DeleteMapping("/delete_category/{categoryId}")
    public ResponseEntity<DeletionSuccessfulResponse> deleteCategory(@PathVariable String categoryId)
            throws CategoryNotFoundException {

        categoryService.deleteCategory(new CategoryId(categoryId));
        return ResponseEntity.ok(DeletionSuccessfulResponse.builder().build());
    }
//    @GetMapping("{category}")
//    public ResponseEntity<CategoryEntity> getCategory(@PathVariable String category) throws CategoryNotFoundException {
//        return ResponseEntity.ok(categoryService.getByCategoryName(category));
//    }
}
