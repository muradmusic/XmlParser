package com.example.demo;

import com.example.demo.Entity.Currency;
import com.example.demo.Entity.ExchangeRates;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(ExchangeRates.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ExchangeRates exchangeRates = (ExchangeRates) unmarshaller.unmarshal(new File("cur.xml"));

            for (Currency currency : exchangeRates.getCurrencies()) {
                System.out.println(currency.getCode() + ": " + currency.getRate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}