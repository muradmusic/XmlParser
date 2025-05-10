package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Current date appended to URL, to get current date's xml
public class UrlGenerator {

    //generate string from LocalDate with today's date
    public static String generateTodayUrl() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = today.format(formatter);

        return "https://cbar.az/currencies/" + formattedDate + ".xml";
    }

    //generate string from LocalDate with selected date
    public static String generateUrlByDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        return "https://cbar.az/currencies/" + formattedDate + ".xml";
    }

}
