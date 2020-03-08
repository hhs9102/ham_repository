package me.ham.validation.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ValidationException  extends RuntimeException{
    private HttpStatus status;
    private String errorMessage;

    public ValidationException(HttpStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
