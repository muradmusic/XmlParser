package com.example.demo.Entity;


import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

/*
This is the root of Xml we are parsing
and attributes, elements of this root.
 */
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ValCurs {

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "Description")
    private String description;

    @XmlElement(name = "ValType")
    List<ValType> valTypes;


}
