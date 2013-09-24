package com.mytutorial.webservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class UserXml {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}