package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDto implements Serializable {

    private String email;
    private String password;

    public LoginDto() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
