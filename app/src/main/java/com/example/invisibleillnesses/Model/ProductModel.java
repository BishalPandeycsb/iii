package com.example.invisibleillnesses.Model;

public class ProductModel {

    private String id;
    private String name;
    private String price;
    private String designer;
    private String size;
    private String refundable;
    private String weekend_hire;
    private String short_description;
    private String description;
    private String photo;

    public ProductModel(String id, String name, String price, String designer, String size, String refundable, String weekend_hire, String short_description, String description, String photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.designer = designer;
        this.size = size;
        this.refundable = refundable;
        this.weekend_hire = weekend_hire;
        this.short_description = short_description;
        this.description = description;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
    }

    public String getWeekend_hire() {
        return weekend_hire;
    }

    public void setWeekend_hire(String weekend_hire) {
        this.weekend_hire = weekend_hire;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", designer='" + designer + '\'' +
                ", size='" + size + '\'' +
                ", refundable='" + refundable + '\'' +
                ", weekend_hire='" + weekend_hire + '\'' +
                ", short_description='" + short_description + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
