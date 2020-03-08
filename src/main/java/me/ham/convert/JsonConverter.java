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
    private JsonConverter(){
    }
    public static class LocalDateTimeDeserialize extends JsonDeserializer<LocalDateTime> {
        private final String format = "yyyy-MM-dd kk:mm:ss";
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(format));
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
