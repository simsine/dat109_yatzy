package no.hvl.dat109.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import no.hvl.dat109.yatzy.PoengType;

import java.util.Map;

@Converter(autoApply = true)
public class PoengConverter implements AttributeConverter<Map<PoengType, Integer>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<PoengType, Integer> attribute) {
        if (attribute == null) {
            return "{}";  // return empty JSON if the attribute is null
        }
        try {
            return objectMapper.writeValueAsString(attribute);  // convert map to JSON string
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<PoengType, Integer> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;  // Return null if dbData is empty or null
        }
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructMapType(Map.class, PoengType.class, Integer.class));  // Convert JSON string back to Map
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to map", e);
        }
    }
}
