package hristovski.nikola.shopping_cart.application.port.exception;

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException(String message){
        super(message);
    }
    public InsufficientQuantityException(String message, Throwable throwable){
        super(message, throwable);
    }
}
