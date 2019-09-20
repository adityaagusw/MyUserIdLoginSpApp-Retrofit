package com.example.crudlogin.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    String id;

    @SerializedName("nama")
    String namauser;

    @SerializedName("jenis_kelamin")
    String jk;

    @SerializedName("username")
    String username;


    public UserModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
