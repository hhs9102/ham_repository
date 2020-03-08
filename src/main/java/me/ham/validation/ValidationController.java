package me.ham.validation;

import me.ham.validation.model.request.ValidationRequest;
import me.ham.validation.validator.ValidationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationValidator validator;

    @GetMapping
    public ResponseEntity<?> validation(@RequestBody @Valid Optional<ValidationRequest> request, Errors errors){
        if(request.isPresent()){
            validator.validate(request.get(), errors);
            if(errors.hasErrors()){
                return ResponseEntity.badRequest().body(errors.getGlobalError());
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
