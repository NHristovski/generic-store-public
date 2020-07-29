package hristovski.nikola.users.exception;

public class FailedToCreateUserException extends RuntimeException {

    public FailedToCreateUserException(){
        super();
    }

    public FailedToCreateUserException(String message){
        super(message);
    }
}
