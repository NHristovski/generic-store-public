package hristovski.nikola.payment.application.exception;

public class PaymentException extends Exception {
    public PaymentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
