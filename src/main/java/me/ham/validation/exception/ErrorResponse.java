package me.ham.validation.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    public ErrorResponse(ValidationException ex, String path) {
        this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss").format(LocalDateTime.now());
        this.status = ex.getStatus().value();
        this.error = ex.getStatus().getReasonPhrase();
        this.message = ex.getErrorMessage();
        this.path = path;
    }
}
