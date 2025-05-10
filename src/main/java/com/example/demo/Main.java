package com.example.demo;

import com.example.demo.Entity.*;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            String dateInput = scanner.nextLine();

            // Parse date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(dateInput, formatter);

            //endpoint of Central Bank of Azerbaijan with appended selected date
            String URL = UrlGenerator.generateUrlByDate(date);
            InputStream stream = new URL(URL).openStream();
            ValCurs valCurs = Parser.parse(stream);

            //selecting currency code
            System.out.println("Please Select Currency Code: ");
            Scanner scanner1 = new Scanner(System.in);
            String filter = scanner1.nextLine().trim().toLowerCase();

            //lists all currency codes... not sure if this is necessary
//            for (ValType valType: valCurs.getValTypes()){
//                for(Valute valute : valType.getValutes()){
//                    System.out.println(valute.getCode());
//                }
//            }

            for (ValType valType : valCurs.getValTypes()) {
                for (Valute valute : valType.getValutes()) {
                    if (valute.getCode().equalsIgnoreCase(filter)) {
                        System.out.println(valute.getCode() + ": Nominal " + valute.getNominal() + ": " + valute.getName() + ": " + valute.getValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
