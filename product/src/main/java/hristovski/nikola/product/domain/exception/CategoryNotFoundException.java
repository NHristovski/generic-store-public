package hristovski.nikola.product.domain.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String categoryId) {
        super("Category with id" + categoryId + " not found!");
    }

    public CategoryNotFoundException(String categoryId, Throwable throwable) {
        super("Category with id" + categoryId + " not found!", throwable);
    }
}
