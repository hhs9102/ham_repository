package me.ham.validation.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import me.ham.convert.JsonConverter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ValidationRequest {
    @NotNull
    @NotEmpty
    @JsonProperty
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserialize.class)
    private LocalDateTime end;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserialize.class)
    private LocalTime time;
}
