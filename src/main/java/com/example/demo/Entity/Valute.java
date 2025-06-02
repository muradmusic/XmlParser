package com.example.demo.Entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/*
XML field
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Valute {

    public Valute() {
    }

    @XmlAttribute(name = "Code")
    private String code;

    @XmlElement(name = "Nominal")
    private String nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    private double value;


    public Valute(String code, String name, String nominal, double value) {
        this.code = code;
        this.name = name;
        this.nominal = nominal;
        this.value = value;
    }

}
