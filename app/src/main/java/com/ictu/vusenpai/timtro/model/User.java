package com.ictu.vusenpai.timtro.model;

import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.DanhGia;

import java.util.List;

public class User {
    private String id;
    private String ho_ten;
    private int avata;
    private String username;
    private String password;
    private String diaChi;
    private String SDT;
    private List<BaiDang> baiDangList;
    private List<DanhGia> danhGiaList;

    public User(String id, String ho_ten, int avata, String username, String password, String diaChi, String SDT, List<BaiDang> baiDangList, List<DanhGia> danhGiaList) {
        this.id = id;
        this.ho_ten = ho_ten;
        this.avata = avata;
        this.username = username;
        this.password = password;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.baiDangList = baiDangList;
        this.danhGiaList = danhGiaList;
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

    public int getAvata() {
        return avata;
    }

    public void setAvata(int avata) {
        this.avata = avata;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<BaiDang> getBaiDangList() {
        return baiDangList;
    }

    public void setBaiDangList(List<BaiDang> baiDangList) {
        this.baiDangList = baiDangList;
    }

    public List<DanhGia> getDanhGiaList() {
        return danhGiaList;
    }

    public void setDanhGiaList(List<DanhGia> danhGiaList) {
        this.danhGiaList = danhGiaList;
    }
}
