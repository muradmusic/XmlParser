package com.example.demo;

import com.example.demo.Entity.*;

import java.io.InputStream;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class reads XML file, parses it into a {@link ValCurs} object using {@link Parser} class
 * and prints the currency exchange info to the console.
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.print("PLease Enter date in format (dd.MM.yyyy): ");
            Scanner scanner = new Scanner(System.in);
            String dateInput;

            // Parse date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date;

            while(true) {
                dateInput = scanner.nextLine();
                try {
                    date = LocalDate.parse(dateInput, formatter);
                    break;
                } catch (DateTimeException e) {
                    System.out.println("Invalid date. Try again. ");
                }
            }

            //endpoint of Central Bank of Azerbaijan with appended selected date
            String URL = UrlGenerator.generateUrlByDate(date);
            InputStream stream = new URL(URL).openStream();
            ValCurs valCurs = Parser.parse(stream);
            stream.close();

            //selecting currency code
            System.out.println("Please Select Currency Code: ");
            String filter = scanner.nextLine().trim().toLowerCase();


            Map<String, Valute> currencyMap = new HashMap<>();

            for(ValType valType : valCurs.getValTypes()){
                for(Valute valute : valType.getValutes()){
                    currencyMap.put(valute.getCode().toUpperCase(), valute);
                }
            }
            Valute result = currencyMap.get(filter.toUpperCase());

            if(result != null){
                System.out.println(result.getCode() + ": Nominal " +
                        result.getNominal() + ": " +
                        result.getName() + ": " +
                        result.getValue());
            }
            else {
                System.out.println("No matching currency code found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
