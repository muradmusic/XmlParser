package com.example.demo;

import com.example.demo.Entity.ValCurs;
import com.example.demo.Entity.ValType;
import com.example.demo.Entity.Valute;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testUnmarshalValCurs() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(reader);

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
}
