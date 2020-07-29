package hristovski.nikola.shopping_cart.application.port.exception;

public class MinQuantityReachedException extends Exception {
    public MinQuantityReachedException(String message){
        super(message);
    }
    public MinQuantityReachedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
