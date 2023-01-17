package com.example.invisibleillnesses.Model;

public class HelpingHandModel {

    private String id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String phone_number;
    private String member_ship_number;
    private String type_of_benefit;
    private String untitled;
    private String radio_financial;
    private String radio_vaccinated;
    private String radio_employed;
    private String radio_centerLink;
    private String radio_assistance;

    public HelpingHandModel(String id, String first_name, String last_name, String address, String email, String phone_number, String member_ship_number, String type_of_benefit, String untitled, String radio_financial, String radio_vaccinated, String radio_employed, String radio_centerLink, String radio_assistance) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.member_ship_number = member_ship_number;
        this.type_of_benefit = type_of_benefit;
        this.untitled = untitled;
        this.radio_financial = radio_financial;
        this.radio_vaccinated = radio_vaccinated;
        this.radio_employed = radio_employed;
        this.radio_centerLink = radio_centerLink;
        this.radio_assistance = radio_assistance;
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

    public String getMember_ship_number() {
        return member_ship_number;
    }

    public void setMember_ship_number(String member_ship_number) {
        this.member_ship_number = member_ship_number;
    }

    public String getType_of_benefit() {
        return type_of_benefit;
    }

    public void setType_of_benefit(String type_of_benefit) {
        this.type_of_benefit = type_of_benefit;
    }

    public String getUntitled() {
        return untitled;
    }

    public void setUntitled(String untitled) {
        this.untitled = untitled;
    }

    public String getRadio_financial() {
        return radio_financial;
    }

    public void setRadio_financial(String radio_financial) {
        this.radio_financial = radio_financial;
    }

    public String getRadio_vaccinated() {
        return radio_vaccinated;
    }

    public void setRadio_vaccinated(String radio_vaccinated) {
        this.radio_vaccinated = radio_vaccinated;
    }

    public String getRadio_employed() {
        return radio_employed;
    }

    public void setRadio_employed(String radio_employed) {
        this.radio_employed = radio_employed;
    }

    public String getRadio_centerLink() {
        return radio_centerLink;
    }

    public void setRadio_centerLink(String radio_centerLink) {
        this.radio_centerLink = radio_centerLink;
    }

    public String getRadio_assistance() {
        return radio_assistance;
    }

    public void setRadio_assistance(String radio_assistance) {
        this.radio_assistance = radio_assistance;
    }

    @Override
    public String toString() {
        return "HelpingHandModel{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", member_ship_number='" + member_ship_number + '\'' +
                ", type_of_benefit='" + type_of_benefit + '\'' +
                ", untitled='" + untitled + '\'' +
                ", radio_financial='" + radio_financial + '\'' +
                ", radio_vaccinated='" + radio_vaccinated + '\'' +
                ", radio_employed='" + radio_employed + '\'' +
                ", radio_centerLink='" + radio_centerLink + '\'' +
                ", radio_assistance='" + radio_assistance + '\'' +
                '}';
    }
}
