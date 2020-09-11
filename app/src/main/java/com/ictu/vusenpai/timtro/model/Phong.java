package com.ictu.vusenpai.timtro.model;

import android.os.Parcel;
import android.os.Parcelable;
public class Phong implements Parcelable {
    private String id;
    private String diaChi;
    private int dienTich;
    private int anhFeeback;
    private int gia;

    public Phong(String id, String diaChi, int dienTich, int anhFeeback, int gia) {
        this.id = id;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.anhFeeback = anhFeeback;
        this.gia = gia;
    }

    protected Phong(Parcel in) {
        id = in.readString();
        diaChi = in.readString();
        dienTich = in.readInt();
        anhFeeback = in.readInt();
        gia = in.readInt();
    }

    public static final Parcelable.Creator<Phong> CREATOR = new Parcelable.Creator<Phong>() {
        @Override
        public Phong createFromParcel(Parcel in) {
            return new Phong(in);
        }

        @Override
        public Phong[] newArray(int size) {
            return new Phong[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAnhFeeback() {
        return anhFeeback;
    }

    public void setAnhFeeback(int anhFeeback) {
        this.anhFeeback = anhFeeback;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(diaChi);
        dest.writeInt(dienTich);
        dest.writeInt(anhFeeback);
        dest.writeInt(gia);
    }
}
