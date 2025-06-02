package com.example.demo.Service;

import com.example.demo.Entity.*;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CurrencyService {

    //fetching appropriate valcurs by the entered date
    public static ValCurs fetchValCursByDate(LocalDate date) throws Exception {
        String URL = UrlGenerator.generateUrlByDate(date);
        try (InputStream stream = new URL(URL).openStream()) {
            return Parser.parse(stream);
        }
    }

    //map storing valcurs by valute codes
    public static Map<String, Valute> generateCurrencyMap(ValCurs valCurs){
        Map<String, Valute> currencyMap = new HashMap<>();
        if(valCurs != null){
            for(ValType valType : valCurs.getValTypes()){
                for(Valute valute : valType.getValutes()){
                    currencyMap.put(valute.getCode().toUpperCase(), valute);
                }
            }
        }
        return currencyMap;
    }
}
