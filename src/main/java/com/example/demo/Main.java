package com.example.demo;

import com.example.demo.Entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * This class reads XML file, parses it into a {@link ValCurs} object using {@link Parser} class
 * and prints the currency exchange info to the console.
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
