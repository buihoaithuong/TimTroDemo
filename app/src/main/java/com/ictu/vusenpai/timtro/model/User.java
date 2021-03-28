package com.ictu.vusenpai.timtro.model;

import java.util.List;

public class User {
    private  String id;
    private String ho_ten;
    private String email;
    private String diaChi;
    private String SDT;
    private List<BaiDang> baiDangList;

    public User() {
    }

    public User(String ho_ten, String email, String diaChi, String SDT, List<BaiDang> baiDangList, String id) {
        this.ho_ten = ho_ten;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.baiDangList = baiDangList;
        this.id=id;
    }

    public User(String ho_ten, String email, String diaChi, String SDT, String id) {
        this.ho_ten = ho_ten;
        this.email = email;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BaiDang> getBaiDangList() {
        return baiDangList;
    }

    public void setBaiDangList(List<BaiDang> baiDangList) {
        this.baiDangList = baiDangList;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
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
