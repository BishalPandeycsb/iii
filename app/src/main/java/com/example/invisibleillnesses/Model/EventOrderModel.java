package com.example.invisibleillnesses.Model;

public class EventOrderModel {
    private String id;
    private String first_name;
    private String last_name;
    private String street_name;
    private String apartment_status;
    private String suburb_value;
    private String post_code;
    private String phone_value;
    private String email_address;
    private String event_id;
    private String event_name;
    private String event_price;
    private String event_location;
    private String event_date;
    private String event_des;
    private String event_photo;

    public EventOrderModel(String id, String first_name, String last_name, String street_name, String apartment_status, String suburb_value, String post_code, String phone_value, String email_address, String event_id, String event_name, String event_price, String event_location, String event_date, String event_des, String event_photo) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street_name = street_name;
        this.apartment_status = apartment_status;
        this.suburb_value = suburb_value;
        this.post_code = post_code;
        this.phone_value = phone_value;
        this.email_address = email_address;
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_price = event_price;
        this.event_location = event_location;
        this.event_date = event_date;
        this.event_des = event_des;
        this.event_photo = event_photo;
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

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getApartment_status() {
        return apartment_status;
    }

    public void setApartment_status(String apartment_status) {
        this.apartment_status = apartment_status;
    }

    public String getSuburb_value() {
        return suburb_value;
    }

    public void setSuburb_value(String suburb_value) {
        this.suburb_value = suburb_value;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getPhone_value() {
        return phone_value;
    }

    public void setPhone_value(String phone_value) {
        this.phone_value = phone_value;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_price() {
        return event_price;
    }

    public void setEvent_price(String event_price) {
        this.event_price = event_price;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_des() {
        return event_des;
    }

    public void setEvent_des(String event_des) {
        this.event_des = event_des;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    @Override
    public String toString() {
        return "EventOrderModel{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", street_name='" + street_name + '\'' +
                ", apartment_status='" + apartment_status + '\'' +
                ", suburb_value='" + suburb_value + '\'' +
                ", post_code='" + post_code + '\'' +
                ", phone_value='" + phone_value + '\'' +
                ", email_address='" + email_address + '\'' +
                ", event_id='" + event_id + '\'' +
                ", event_name='" + event_name + '\'' +
                ", event_price='" + event_price + '\'' +
                ", event_location='" + event_location + '\'' +
                ", event_date='" + event_date + '\'' +
                ", event_des='" + event_des + '\'' +
                ", event_photo='" + event_photo + '\'' +
                '}';
    }
}
