package com.example.demo.Entity;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.Reader;

/*
This class is responsible for converting XML input
into  ValCurs object using JAXB.
 */
public class Parser {
    /*
We implement unmarshalling
logic in this method, in order to be able to test the parsing
separately.
     */
    public static ValCurs parse(Reader reader) throws Exception {
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ValCurs) unmarshaller.unmarshal(reader);
    }
}
