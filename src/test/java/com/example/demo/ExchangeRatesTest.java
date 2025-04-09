package com.example.demo;

import com.example.demo.Entity.Currency;
import com.example.demo.Entity.ExchangeRates;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

    class ExchangeRatesTest {

        private final String xml = """
                <ExchangeRates>
                    <Currency code="USD">
                        <Rate>1.12</Rate>
                    </Currency>
                    <Currency code="EUR">
                        <Rate>1.00</Rate>
                    </Currency>
                </ExchangeRates>
                """;

        @Test
        void testUnmarshalExchangeRates() throws Exception {
            JAXBContext context = JAXBContext.newInstance(ExchangeRates.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(xml);
            ExchangeRates exchangeRates = (ExchangeRates) unmarshaller.unmarshal(reader);

            List<Currency> currencies = exchangeRates.getCurrencies();
            assertNotNull(currencies);
            assertEquals(2, currencies.size());

            Currency usd = currencies.stream()
                    .filter(c -> "USD".equals(c.getCode()))
                    .findFirst()
                    .orElseThrow();

            assertEquals(1.12, usd.getRate(), 0.0001);

            Currency eur = currencies.stream()
                    .filter(c -> "EUR".equals(c.getCode()))
                    .findFirst()
                    .orElseThrow();

            assertEquals(1.00, eur.getRate(), 0.0001);
        }
    }

