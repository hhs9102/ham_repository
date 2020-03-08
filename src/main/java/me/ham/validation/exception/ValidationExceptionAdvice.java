package me.ham.validation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ValidationExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidatoinException(ValidationException ex, HttpServletRequest request){
        log.error("{} ::: 에러가 발생했습니다.");
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatus());
        errorResponseResponseEntity.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return errorResponseResponseEntity;
    }
}
