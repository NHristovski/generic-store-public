package hristovski.nikola.generic_store.message.domain.validator;


import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, RegisterRequest> {
    public void initialize(PasswordsMatch constraint) {
    }

    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext context) {
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}
