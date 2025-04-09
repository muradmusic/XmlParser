package com.example.demo.Entity;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Currency {
    @XmlAttribute(name = "code")
    private String code;

    @XmlElement(name = "Rate")
    private double rate;

}
