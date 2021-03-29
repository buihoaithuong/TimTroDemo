package com.ictu.vusenpai.timtro.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User  {
    private  String id;
    private String ho_ten;
    private String email;
    private String diaChi;
    private String SDT;

    public User() {
    }

    public User(String id, String ho_ten, String email, String diaChi, String SDT) {
        this.id = id;
        this.ho_ten = ho_ten;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
