package me.ham.validation.validator;


import me.ham.validation.model.request.ValidationRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ValidationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationRequest validationRequest = (ValidationRequest)target;
        if(validationRequest.getStart().isAfter(validationRequest.getEnd())){
            errors.reject("400", "period is not valid");
        }
    }
}
