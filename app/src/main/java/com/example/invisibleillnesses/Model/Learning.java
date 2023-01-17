package com.example.invisibleillnesses.Model;

public class Learning {

    private String id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String phone_number;
    private String suburb;
    private String explain;
    private String birthday;
    private String child_help;
    private String comment;
    private String gender;
    private String who_help;

    public Learning(String id, String first_name, String last_name, String address, String email, String phone_number, String suburb, String explain, String birthday, String child_help, String comment, String gender, String who_help) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.suburb = suburb;
        this.explain = explain;
        this.birthday = birthday;
        this.child_help = child_help;
        this.comment = comment;
        this.gender = gender;
        this.who_help = who_help;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getChild_help() {
        return child_help;
    }

    public void setChild_help(String child_help) {
        this.child_help = child_help;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWho_help() {
        return who_help;
    }

    public void setWho_help(String who_help) {
        this.who_help = who_help;
    }

    @Override
    public String toString() {
        return "Learning{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", suburb='" + suburb + '\'' +
                ", explain='" + explain + '\'' +
                ", birthday='" + birthday + '\'' +
                ", child_help='" + child_help + '\'' +
                ", comment='" + comment + '\'' +
                ", gender='" + gender + '\'' +
                ", who_help='" + who_help + '\'' +
                '}';
    }
}
