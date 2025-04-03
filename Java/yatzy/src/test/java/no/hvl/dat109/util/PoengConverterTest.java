package no.hvl.dat109.util;

import no.hvl.dat109.yatzy.PoengType;
import no.hvl.dat109.util.PoengConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PoengConverterTest {

    private PoengConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PoengConverter();
    }

    @Test
    void testConvertToDatabaseColumn() {
        // Sett opp et eksempel Map
        Map<PoengType, Integer> poeng = new HashMap<>();
        poeng.put(PoengType.ENERE, 3);
        poeng.put(PoengType.TOERE, 4);
        
        // Konverter Map til JSON
        String json = converter.convertToDatabaseColumn(poeng);

        // Test at JSON ikke er null og har riktig format
        assertNotNull(json);
        assertEquals("{\"TOERE\":4},\"ENERE\":3}", json);
    }

    @Test
    void testConvertToEntityAttribute() {
        // Sett opp et JSON-streng
        String json = "{\"ENERE\":3,\"TOERE\":4}";
        
        // Konverter JSON tilbake til Map
        Map<PoengType, Integer> poeng = converter.convertToEntityAttribute(json);

        // Test at Map ikke er null og inneholder riktige verdier
        assertNotNull(poeng);
        assertEquals(3, poeng.get(PoengType.ENERE));
        assertEquals(4, poeng.get(PoengType.TOERE));
    }

    @Test
    void testEmptyMapConversion() {
        // Test for tom Map
        Map<PoengType, Integer> emptyMap = new HashMap<>();
        String json = converter.convertToDatabaseColumn(emptyMap);
        assertEquals("{}", json);

        Map<PoengType, Integer> result = converter.convertToEntityAttribute(json);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testNullMapConversion() {
        // Test for null Map
        String json = converter.convertToDatabaseColumn(null);
        assertEquals("{}", json); // Null bør konverteres til en tom JSON-streng

        Map<PoengType, Integer> result = converter.convertToEntityAttribute(json);
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
