package com.example.demo;

import com.example.demo.Entity.*;
import java.io.InputStream;
import java.net.URL;

/**
This class reads "az.xml" file, parses it into a {@link ValCurs} object using {@link Parser} class
 and prints the currency exchange info to the console.
 */
public class Main {
    public static void main(String[] args) {
        try {
            //endpoint of Central Bank of Azerbaijan with appended current date
            String URL = UrlGenerator.generateTodayUrl();
            InputStream stream = new URL(URL).openStream();
            ValCurs valCurs = Parser.parse(stream);

            for (ValType valType : valCurs.getValTypes()) {
                System.out.println(valType.getType());
                for (Valute valute : valType.getValutes()) {
                    System.out.println(valute.getCode() + ": Nominal " + valute.getNominal() + ": " + valute.getName() + ": " + valute.getValue());
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
