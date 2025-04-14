package com.example.demo;

import com.example.demo.Entity.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new File("az.xml"));

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
