package com.example.invisibleillnesses.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderCartModel implements Parcelable {

    private String id;
    private String product_id;
    private String product_name;
    private String product_price;
    private String product_designer;
    private String product_view_size;
    private String product_view_refund;
    private String product_weekend;
    private String product_view_short_des;
    private String product_view_des;
    private String product_view_image;
    private String user_id;
    private String user_email;
    private String product_choose_date;

    protected OrderCartModel(Parcel in) {
        id = in.readString();
        product_id = in.readString();
        product_name = in.readString();
        product_price = in.readString();
        product_designer = in.readString();
        product_view_size = in.readString();
        product_view_refund = in.readString();
        product_weekend = in.readString();
        product_view_short_des = in.readString();
        product_view_des = in.readString();
        product_view_image = in.readString();
        user_id = in.readString();
        user_email = in.readString();
        product_choose_date = in.readString();
    }

    public static final Creator<OrderCartModel> CREATOR = new Creator<OrderCartModel>() {
        @Override
        public OrderCartModel createFromParcel(Parcel in) {
            return new OrderCartModel(in);
        }

        @Override
        public OrderCartModel[] newArray(int size) {
            return new OrderCartModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_designer() {
        return product_designer;
    }

    public void setProduct_designer(String product_designer) {
        this.product_designer = product_designer;
    }

    public String getProduct_view_size() {
        return product_view_size;
    }

    public void setProduct_view_size(String product_view_size) {
        this.product_view_size = product_view_size;
    }

    public String getProduct_view_refund() {
        return product_view_refund;
    }

    public void setProduct_view_refund(String product_view_refund) {
        this.product_view_refund = product_view_refund;
    }

    public String getProduct_weekend() {
        return product_weekend;
    }

    public void setProduct_weekend(String product_weekend) {
        this.product_weekend = product_weekend;
    }

    public String getProduct_view_short_des() {
        return product_view_short_des;
    }

    public void setProduct_view_short_des(String product_view_short_des) {
        this.product_view_short_des = product_view_short_des;
    }

    public String getProduct_view_des() {
        return product_view_des;
    }

    public void setProduct_view_des(String product_view_des) {
        this.product_view_des = product_view_des;
    }

    public String getProduct_view_image() {
        return product_view_image;
    }

    public void setProduct_view_image(String product_view_image) {
        this.product_view_image = product_view_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getProduct_choose_date() {
        return product_choose_date;
    }

    public void setProduct_choose_date(String product_choose_date) {
        this.product_choose_date = product_choose_date;
    }

    @Override
    public String toString() {
        return "OrderCartModel{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_designer='" + product_designer + '\'' +
                ", product_view_size='" + product_view_size + '\'' +
                ", product_view_refund='" + product_view_refund + '\'' +
                ", product_weekend='" + product_weekend + '\'' +
                ", product_view_short_des='" + product_view_short_des + '\'' +
                ", product_view_des='" + product_view_des + '\'' +
                ", product_view_image='" + product_view_image + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_email='" + user_email + '\'' +
                ", product_choose_date='" + product_choose_date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(product_id);
        parcel.writeString(product_name);
        parcel.writeString(product_price);
        parcel.writeString(product_designer);
        parcel.writeString(product_view_size);
        parcel.writeString(product_view_refund);
        parcel.writeString(product_weekend);
        parcel.writeString(product_view_short_des);
        parcel.writeString(product_view_des);
        parcel.writeString(product_view_image);
        parcel.writeString(user_id);
        parcel.writeString(user_email);
        parcel.writeString(product_choose_date);
    }
}
