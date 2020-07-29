package hristovski.nikola.encryption.application.exception;

public class EncryptionException extends Exception {
    public EncryptionException(String message){
        super(message);
    }
    public EncryptionException(String message, Throwable throwable){
        super(message,throwable);
    }
}
