package hristovski.nikola.product.domain.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String id) {
        super("Product with id " + id + " not found!");
    }

    public ProductNotFoundException(String id, Throwable throwable) {
        super("Product with id " + id + " not found!", throwable);
    }
}
