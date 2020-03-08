package me.ham.validation;

import me.ham.validation.exception.ValidationException;
import me.ham.validation.model.request.ValidationRequest;
import me.ham.validation.validator.ValidationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationValidator validator;

    @GetMapping
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> validation(@RequestBody @Valid Optional<ValidationRequest> request, Errors errors){
        if(request.isPresent()){
            validator.validate(request.get(), errors);
            if(errors.hasErrors()){
                throw new ValidationException(HttpStatus.BAD_REQUEST, errors.getGlobalError().getDefaultMessage());
            }
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/basic")
    public ResponseEntity<?> validation(@RequestBody(required = false) @Valid ValidationRequest request){
        return ResponseEntity.ok().build();
    }
}
