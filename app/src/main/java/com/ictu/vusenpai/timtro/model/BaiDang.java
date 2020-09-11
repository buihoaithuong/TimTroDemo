package com.ictu.vusenpai.timtro.model;
import android.os.Parcel;
import android.os.Parcelable;


public class BaiDang implements Parcelable {
    private String id;
    private String tieuDe;
    private String thoiGianDang;
    private Phong phong;

    public BaiDang(String id, String tieuDe, String thoiGianDang, Phong phong) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.thoiGianDang = thoiGianDang;
        this.phong = phong;
    }

    protected BaiDang(Parcel in) {
        id = in.readString();
        tieuDe = in.readString();
        thoiGianDang = in.readString();
        phong=in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<BaiDang> CREATOR = new Parcelable.Creator<BaiDang>() {
        @Override
        public BaiDang createFromParcel(Parcel in) {
            return new BaiDang(in);
        }

        @Override
        public BaiDang[] newArray(int size) {
            return new BaiDang[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getThoiGianDang() {
        return thoiGianDang;
    }

    public void setThoiGianDang(String thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tieuDe);
        dest.writeString(thoiGianDang);
        dest.writeParcelable(phong, flags);
    }
}
