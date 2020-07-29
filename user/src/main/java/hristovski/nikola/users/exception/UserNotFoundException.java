package hristovski.nikola.users.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Failed to find user";
    public UserNotFoundException(){
        super(MESSAGE);
    }

    public UserNotFoundException(String username){
        super(MESSAGE + " with username " + username);
    }
}
