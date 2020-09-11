package com.ictu.vusenpai.timtro.model;


import java.util.List;

public class DanhGia {
    private String danhGia;
    private List<Integer> anhDanhGia;
    private String id_danhgia;

    public DanhGia( String danhGia, List<Integer> anhDanhGia, String id_danhgia) {
        this.danhGia = danhGia;
        this.anhDanhGia = anhDanhGia;
        this.id_danhgia = id_danhgia;
    }
    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public List<Integer> getAnhDanhGia() {
        return anhDanhGia;
    }

    public void setAnhDanhGia(List<Integer> anhDanhGia) {
        this.anhDanhGia = anhDanhGia;
    }

    public String getId_danhgia() {
        return id_danhgia;
    }

    public void setId_danhgia(String id_danhgia) {
        this.id_danhgia = id_danhgia;
    }
}
