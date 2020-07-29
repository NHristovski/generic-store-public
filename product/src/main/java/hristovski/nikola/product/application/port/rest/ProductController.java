package hristovski.nikola.product.application.port.rest;

import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.common.response.DeletionSuccessfulResponse;
import hristovski.nikola.generic_store.message.domain.rest.product.request.AddProductRequest;
import hristovski.nikola.generic_store.message.domain.rest.product.request.EditProductRequest;
import hristovski.nikola.generic_store.message.domain.rest.product.response.GetOnePersonalizedProductResponse;
import hristovski.nikola.generic_store.message.domain.rest.product.response.GetPersonalizedProductsPageResponse;
import hristovski.nikola.generic_store.message.domain.rest.product.response.ProductSavedResponse;
import hristovski.nikola.generic_store.message.domain.rest.product.response.ProductsSearchResponse;
import hristovski.nikola.product.domain.exception.CategoryNotFoundException;
import hristovski.nikola.product.domain.exception.ProductNotFoundException;
import hristovski.nikola.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    //    @PostMapping("/add")
//    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {
//
//        return ResponseEntity.ok(productService.addProduct(addProductRequest));
//    }
    @PostMapping("/add_product")
    public ResponseEntity<ProductSavedResponse> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {

        return ResponseEntity.ok(
                ProductSavedResponse.builder()
                        .product(
                                productService.addProduct(
                                        addProductRequest.getImageLocation(),
                                        addProductRequest.getInformation(),
                                        addProductRequest.getPrice(),
                                        addProductRequest.getStock(),
                                        addProductRequest.getCategories()
                                ).toProduct()
                        ).build()
        );
    }

    @PutMapping("/edit_product")
    public ResponseEntity<ProductSavedResponse> editProduct(
            @Valid @RequestBody EditProductRequest editProductRequest
    ) throws ProductNotFoundException {

        return ResponseEntity.ok(
                ProductSavedResponse.builder()
                        .product(
                                productService.editProduct(
                                        editProductRequest.getProduct()
                                ).toProduct()
                        )
                        .build()
        );
    }

    @DeleteMapping("/delete_product/{productId}")
    public ResponseEntity<DeletionSuccessfulResponse> deleteProduct(@PathVariable String productId)
            throws ProductNotFoundException {

        productService.deleteProduct(new ProductId(productId));

        return ResponseEntity.ok(DeletionSuccessfulResponse.builder().build());
    }

    @GetMapping("/{applicationUserId}")
    public ResponseEntity<GetPersonalizedProductsPageResponse> getPersonalizedProductsPage(
            @PathVariable String applicationUserId,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {

        List<PersonalizedProduct> products =
                productService.getProducts(page, size, new ApplicationUserId(applicationUserId));

        int maxPages = productService.maxPages(size);

        return ResponseEntity.ok(new GetPersonalizedProductsPageResponse(products, maxPages));
    }

    @GetMapping("/{productId}/{applicationUserId}")
    public ResponseEntity<GetOnePersonalizedProductResponse> getOnePersonalizedProduct(
            @PathVariable String productId,
            @PathVariable String applicationUserId
    ) throws ProductNotFoundException {

        return ResponseEntity.ok(
                GetOnePersonalizedProductResponse.builder()
                        .personalizedProduct(
                                productService.getById(
                                        new ProductId(productId),
                                        new ApplicationUserId(applicationUserId))
                        )
                        .build()
        );
    }

    @GetMapping("/in_category/{categoryId}/{applicationUserId}")
    public ResponseEntity<GetPersonalizedProductsPageResponse> getPersonalizedProductsInCategoryPage(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @PathVariable String categoryId,
            @PathVariable String applicationUserId
    ) throws CategoryNotFoundException {

        CategoryId catId = new CategoryId(categoryId);

        return ResponseEntity.ok(
                GetPersonalizedProductsPageResponse.builder()
                        .personalizedProducts(
                                productService.getProductsInCategory(
                                        page,
                                        size,
                                        catId,
                                        new ApplicationUserId(applicationUserId)
                                )
                        )
                        .maxPages(
                                productService.maxPagesInCategory(size, catId)
                        )
                        .build()

        );
    }

    @GetMapping("/top_rated/{applicationUserId}")
    public ResponseEntity<GetPersonalizedProductsPageResponse> getAllTopRated(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @PathVariable String applicationUserId
    ) {
        return ResponseEntity.ok(
                GetPersonalizedProductsPageResponse.builder()
                        .personalizedProducts(
                                productService.getProductsSortedByRating(
                                        page,
                                        size,
                                        new ApplicationUserId(applicationUserId))
                        )
                        .maxPages(productService.maxPages(size))
                        .build()
        );
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<ProductsSearchResponse> search(@PathVariable String query) {
        return ResponseEntity.ok(
                ProductsSearchResponse.builder()
                        .products(productService.search(query))
                        .build()
        );
    }
}
