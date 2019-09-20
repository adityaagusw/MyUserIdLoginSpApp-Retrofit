package com.example.crudlogin.Model;

import com.google.gson.annotations.SerializedName;

public class PesanModel {


    @SerializedName("id")
    String id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("pesan")
    String pesan;

    @SerializedName("value")
    String value;

    @SerializedName("message")
    String message;

    public PesanModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
