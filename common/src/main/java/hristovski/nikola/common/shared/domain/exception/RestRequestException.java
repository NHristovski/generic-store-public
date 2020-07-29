package hristovski.nikola.common.shared.domain.exception;

public class RestRequestException extends Exception {
    public RestRequestException() {
        super("The REST request failed.");
    }

    public RestRequestException(String message) {
        super(message);
    }

    public RestRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
