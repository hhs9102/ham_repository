package me.ham.convert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonConverter{
    private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";

    private JsonConverter(){
    }
    public static class LocalDateTimeDeserialize extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
        }

    }
    public static class LocalTimeDeserialize extends JsonDeserializer<LocalTime> {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("h:mm a");
        @Override
        public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getValueAsString();
            return LocalTime.parse(value, FORMATTER);
        }

    }
}
