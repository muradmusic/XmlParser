package com.example.demo;

import com.example.demo.Entity.Parser;
import com.example.demo.Entity.ValCurs;
import com.example.demo.Entity.ValType;
import com.example.demo.Entity.Valute;
import com.example.demo.Service.CurrencyService;
import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for {@link Parser} which converts Xml into {@link ValCurs} objects
 */
public class ValCursTest {
    private final String xml = """
            <ValCurs Date="11.04.2025" Name="AZN məzənnələri" Description="Test">
                <ValType Type="TestType">
                    <Valute Code="USD">
                        <Nominal>1</Nominal>
                        <Name>US Dollar</Name>
                        <Value>1.7</Value>
                    </Valute>
                    <Valute Code="EUR">
                        <Nominal>1</Nominal>
                        <Name>Euro</Name>
                        <Value>1.9</Value>
                    </Valute>
                </ValType>
            </ValCurs>
            """;

    //testing parse method of parser class which is responsible for parsing xml
    @Test
    void testParserValCurs() throws Exception {

        StringReader reader = new StringReader(xml);
        ValCurs valCurs = Parser.parse(reader);

        assertEquals("11.04.2025", valCurs.getDate());
        assertEquals("AZN məzənnələri", valCurs.getName());
        assertEquals("Test", valCurs.getDescription());

        List<ValType> valTypes = valCurs.getValTypes();
        assertNotNull(valTypes);
        assertEquals(1, valTypes.size());

        ValType valType = valTypes.get(0);
        assertEquals("TestType", valType.getType());

        List<Valute> valutes = valType.getValutes();
        assertEquals(2, valutes.size());

        Valute usd = valutes.stream()
                .filter(v -> "USD".equals(v.getCode()))
                .findFirst()
                .orElseThrow();

        assertEquals("1", usd.getNominal());
        assertEquals("US Dollar", usd.getName());
        assertEquals(1.7, usd.getValue(), 0.0001);

        Valute eur = valutes.stream()
                .filter(v -> "EUR".equals(v.getCode()))
                .findFirst()
                .orElseThrow();

        assertEquals("1", eur.getNominal());
        assertEquals("Euro", eur.getName());
        assertEquals(1.9, eur.getValue(), 0.0001);
    }

    @Test
    public void testBuildCurrencyMap() {
        Valute usd = new Valute("USD", "US Dollar", "1", 1.7);
        ValType valType = new ValType();
        valType.setValutes(List.of(usd));

        ValCurs valCurs = new ValCurs();
        valCurs.setValTypes(List.of(valType));

        Map<String, Valute> result = CurrencyService.generateCurrencyMap(valCurs);
        assertEquals(1, result.size());
        assertTrue(result.containsKey("USD"));
        assertEquals("US Dollar", result.get("USD").getName());
    }

    @Test
    public void testFetchValCursByDate_valid() throws Exception {
        LocalDate date = LocalDate.of(2024, 5, 20);
        ValCurs valCurs = CurrencyService.fetchValCursByDate(date);
        assertNotNull(valCurs);
        assertFalse(valCurs.getValTypes().isEmpty());
    }
}
