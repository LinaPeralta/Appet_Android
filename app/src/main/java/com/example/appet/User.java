package com.example.appet;

public class User {

    private String email;
    private String id;
    private String lastname;
    private String name;
    private String password;

    public User() {
    }

    public User(String email, String id, String lastname, String name, String password) {
        this.email = email;
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}