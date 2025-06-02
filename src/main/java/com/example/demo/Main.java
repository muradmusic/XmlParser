package com.example.demo;

import com.example.demo.Entity.*;
import com.example.demo.Service.CurrencyService;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            //fetching appropriate valcurs by the entered date
            ValCurs valCurs = CurrencyService.fetchValCursByDate(date);

            //selecting currency code
            System.out.println("Please Select Currency Code: ");
            String filter = scanner.nextLine().trim().toLowerCase();

            //map storing valcurs by valute codes
            Map<String, Valute> currencyMap = CurrencyService.generateCurrencyMap(valCurs);

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
