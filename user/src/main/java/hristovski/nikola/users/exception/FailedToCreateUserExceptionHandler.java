package hristovski.nikola.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO CREATE THIS REST HANDLER
@ControllerAdvice
public class FailedToCreateUserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedToCreateUserException.class)
    public void handleFailedToCreateUserException(FailedToCreateUserException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
}
