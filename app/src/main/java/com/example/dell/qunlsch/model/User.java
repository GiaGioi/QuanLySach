package com.example.dell.qunlsch.model;

public class User {

    String username;
    String password;
    String name;
    String sdt;

    public User(String username, String password, String name, String sdt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sdt = sdt;
    }

    public User() {
        // Empty User Constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
