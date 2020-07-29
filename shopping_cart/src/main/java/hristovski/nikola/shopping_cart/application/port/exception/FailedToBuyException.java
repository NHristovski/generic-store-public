package hristovski.nikola.shopping_cart.application.port.exception;

public class FailedToBuyException extends Exception {
    public FailedToBuyException(String message){
        super(message);
    }
    public FailedToBuyException(String message, Throwable throwable){
        super(message, throwable);
    }
}


