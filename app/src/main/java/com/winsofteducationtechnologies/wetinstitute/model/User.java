package com.winsofteducationtechnologies.wetinstitute.model;

public class User {
    private int id;
    private String name, email, gender, role;

    public User(int id, String name, String email, String gender, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }
}
