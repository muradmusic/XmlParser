package com.example.demo;

import com.example.demo.Entity.*;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("az.xml");
            ValCurs valCurs = Parser.parse(reader);

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
