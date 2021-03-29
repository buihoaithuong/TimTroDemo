package com.ictu.vusenpai.timtro.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;


public class BaiDang implements Parcelable {
    private String tieuDe;
    private String diaChi;
    private int dienTich;
    private ArrayList<String> anhFeeback;
    private int gia;
    private String idBaiDang;
    private String idUser;
    private String timeDang;

    public BaiDang() {
    }

    public BaiDang(String tieuDe, String diaChi, int dienTich, ArrayList<String> anhFeeback, int gia, String idBaiDang, String idUser, String time) {
        this.tieuDe = tieuDe;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.anhFeeback = anhFeeback;
        this.gia = gia;
        this.idBaiDang = idBaiDang;
        this.idUser = idUser;
        this.timeDang = time;
    }

    protected BaiDang(Parcel in) {
        tieuDe = in.readString();
        diaChi = in.readString();
        dienTich = in.readInt();
        anhFeeback = in.createStringArrayList();
        gia = in.readInt();
        idBaiDang = in.readString();
        idUser = in.readString();
    }

    public static final Creator<BaiDang> CREATOR = new Creator<BaiDang>() {
        @Override
        public BaiDang createFromParcel(Parcel in) {
            return new BaiDang(in);
        }

        @Override
        public BaiDang[] newArray(int size) {
            return new BaiDang[size];
        }
    };

    public String getTimeDang() {
        return timeDang;
    }

    public void setTimeDang(String timeDang) {
        this.timeDang = timeDang;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public ArrayList<String> getAnhFeeback() {
        return anhFeeback;
    }

    public void setAnhFeeback(ArrayList<String> anhFeeback) {
        this.anhFeeback = anhFeeback;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getIdBaiDang() {
        return idBaiDang;
    }

    public void setIdBaiDang(String idBaiDang) {
        this.idBaiDang = idBaiDang;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tieuDe);
        dest.writeString(diaChi);
        dest.writeInt(dienTich);
        dest.writeStringList(anhFeeback);
        dest.writeInt(gia);
        dest.writeString(idBaiDang);
        dest.writeString(idUser);
    }
}
