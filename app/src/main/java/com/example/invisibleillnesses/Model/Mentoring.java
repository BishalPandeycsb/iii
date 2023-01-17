package com.example.invisibleillnesses.Model;

public class Mentoring {

    private String id;
    private String first_name;
    private String suburb;
    private String email;
    private String phone_number;
    private String untitled;

    public Mentoring(String id, String first_name, String suburb, String email, String phone_number, String untitled) {
        this.id = id;
        this.first_name = first_name;
        this.suburb = suburb;
        this.email = email;
        this.phone_number = phone_number;
        this.untitled = untitled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUntitled() {
        return untitled;
    }

    public void setUntitled(String untitled) {
        this.untitled = untitled;
    }

    @Override
    public String toString() {
        return "Mentoring{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", suburb='" + suburb + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", untitled='" + untitled + '\'' +
                '}';
    }
}
