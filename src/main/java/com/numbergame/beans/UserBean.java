package com.numbergame.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserBean implements Serializable {
    private String name = "";
    private String message;
    public UserBean() {
        this.name = "";
        this.message = "Bienvenid@ ";
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message + name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
