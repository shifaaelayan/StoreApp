package com.example.store.model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public User(int id,String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId(){return id;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {return password;};

    public String getPhone() {return phone;}


}
