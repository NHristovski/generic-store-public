package hristovski.nikola.product.application.port.rest;

import hristovski.nikola.common.shared.domain.constants.Headers;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
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

import javax.servlet.http.HttpServletRequest;
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
                                        addProductRequest.getProduct().getImageLocation(),
                                        addProductRequest.getProduct().getInformation(),
                                        addProductRequest.getProduct().getPrice(),
                                        addProductRequest.getProduct().getCategories(),
                                        addProductRequest.getProduct().getStock()
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

    @PutMapping("/refresh/{productId}")
    public ResponseEntity<ProductSavedResponse> refreshProduct(@PathVariable String productId)
            throws ProductNotFoundException {

        return ResponseEntity.ok(
                ProductSavedResponse.builder()
                        .product(
                                productService.refreshProduct(
                                        new ProductId(productId)
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

    // TODO VERIFY THE STOCK IS RETURNED FOR EVERYONE
    @GetMapping()
    public ResponseEntity<GetPersonalizedProductsPageResponse> getPersonalizedProductsPage(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            HttpServletRequest httpRequest) throws RestRequestException {

        String userId = httpRequest.getHeader(Headers.USER_ID);

        log.info("GetPersonalizedProductsPage called from user {}", userId);

        List<PersonalizedProduct> products =
                productService.getProducts(page, size, new ApplicationUserId(userId));

        int maxPages = productService.maxPages(size);

        return ResponseEntity.ok(new GetPersonalizedProductsPageResponse(products, maxPages));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetOnePersonalizedProductResponse> getOnePersonalizedProduct(
            @PathVariable String productId, HttpServletRequest httpRequest)
            throws ProductNotFoundException, RestRequestException {

        String userId = httpRequest.getHeader(Headers.USER_ID);

        return ResponseEntity.ok(
                GetOnePersonalizedProductResponse.builder()
                        .personalizedProduct(
                                productService.getById(
                                        new ProductId(productId),
                                        new ApplicationUserId(userId))
                        )
                        .build()
        );
    }

    @GetMapping("/in_category/{categoryId}")
    public ResponseEntity<GetPersonalizedProductsPageResponse> getPersonalizedProductsInCategoryPage(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @PathVariable String categoryId,
            HttpServletRequest httpRequest) throws RestRequestException, CategoryNotFoundException {

        String userId = httpRequest.getHeader(Headers.USER_ID);

        CategoryId catId = new CategoryId(categoryId);

        return ResponseEntity.ok(
                GetPersonalizedProductsPageResponse.builder()
                        .personalizedProducts(
                                productService.getProductsInCategory(
                                        page,
                                        size,
                                        catId,
                                        new ApplicationUserId(userId)
                                )
                        )
                        .maxPages(
                                productService.maxPagesInCategory(size, catId)
                        )
                        .build()

        );
    }

    @GetMapping("/top_rated")
    public ResponseEntity<GetPersonalizedProductsPageResponse> getAllTopRated(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            HttpServletRequest httpRequest) throws RestRequestException {

        String userId = httpRequest.getHeader(Headers.USER_ID);

        return ResponseEntity.ok(
                GetPersonalizedProductsPageResponse.builder()
                        .personalizedProducts(
                                productService.getProductsSortedByRating(
                                        page,
                                        size,
                                        new ApplicationUserId(userId))
                        )
                        .maxPages(productService.maxPages(size))
                        .build()
        );
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<ProductsSearchResponse> search(@PathVariable String query) throws RestRequestException {
        return ResponseEntity.ok(
                ProductsSearchResponse.builder()
                        .products(productService.search(query))
                        .build()
        );
    }
}
