package hristovski.nikola.shopping_cart.application.port.exception;

public class MaxQuantityReachedException extends Exception {
    public MaxQuantityReachedException(String message){
        super(message);
    }
    public MaxQuantityReachedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
