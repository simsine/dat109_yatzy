package no.hvl.dat109.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import no.hvl.dat109.yatzy.PoengType;

@Converter
public class PoengConverter implements AttributeConverter<Map<PoengType, Integer>, String> {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Map<PoengType, Integer> attribute) {
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error converting map to JSON", e);
		}
	}

	@Override
	public Map<PoengType, Integer> convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, new TypeReference<Map<PoengType, Integer>>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error converting JSON to map", e);
		}
	}
	
}
