//package hristovski.nikola.product.controllers;
//
//import hristovski.nikola.common.shared.message.category.request.AddCategoryRequest;
//import hristovski.nikola.common.shared.message.category.response.CategorySavedResponse;
//import hristovski.nikola.common.shared.message.common.response.DeletionSuccessfulResponse;
//import hristovski.nikola.common.shared.message.product.request.AddProductRequest;
//import hristovski.nikola.common.shared.message.product.request.EditProductRequest;
//import hristovski.nikola.common.shared.message.product.response.ProductSavedResponse;
//import hristovski.nikola.common.shared.domain.model.category.CategoryId;
//import hristovski.nikola.common.shared.domain.model.product.ProductId;
//import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
//import hristovski.nikola.product.domain.exception.ProductNotFoundException;
//import hristovski.nikola.product.domain.model.product.ProductEntity;
//import hristovski.nikola.product.domain.service.CategoryService;
//import hristovski.nikola.product.domain.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin")
//@RequiredArgsConstructor
//@Slf4j
//public class AdminController {
//
//    private final ProductService productService;
//    private final CategoryService categoryService;
//
////    @GetMapping("/all_categories")
////    public ResponseEntity<GetAllCategoriesResponse> getAllCategories() {
////        GetAllCategoriesResponse.builder()
////                .categories(categoryService.getAll())
////        return ResponseEntity.ok();
////    }
//
//}
