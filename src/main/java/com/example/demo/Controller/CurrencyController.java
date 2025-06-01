package com.example.demo.Controller;


import com.example.demo.Entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

@GetMapping("/currency")
public String getCurrency(@RequestParam(required = false) String date,
                          @RequestParam(required = false) String code,
                          Model model) {

    if (date == null || code == null || date.isBlank() || code.isBlank()) {
        model.addAttribute("error", "Both date and currency code are required.");
        return "index";
    }

    LocalDate parsedDate;
    try {
        parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("date", parsedDate);
    } catch (Exception e) {
        model.addAttribute("error", "Invalid date format. Please use dd.MM.yyyy.");
        return "index";
    }

    try {
        String url = UrlGenerator.generateUrlByDate(parsedDate);
        InputStream stream = new URL(url).openStream();
        ValCurs valCurs = Parser.parse(stream);
        stream.close();

        for (ValType valType : valCurs.getValTypes()) {
            for (Valute valute : valType.getValutes()) {
                if (valute.getCode().equalsIgnoreCase(code)) {
                    model.addAttribute("valute", valute);
                    return "index";
                }
            }
        }

        model.addAttribute("error", "Currency code not found.");
    } catch (Exception e) {
        model.addAttribute("error", "Server error: " + e.getMessage());
    }

    return "index";
}

}
