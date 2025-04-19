package com.example.demo.Entity;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.Reader;

public class Parser {
    public static ValCurs parse(Reader reader) throws Exception {
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ValCurs) unmarshaller.unmarshal(reader);
    }

}
