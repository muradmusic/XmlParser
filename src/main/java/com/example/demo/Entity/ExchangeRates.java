package com.example.demo.Entity;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@XmlRootElement(name = "ExchangeRates")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRates {

    @XmlElement(name = "Currency")
    private List<Currency> currencies;

}