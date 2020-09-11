package com.ictu.vusenpai.timtro.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.model.BaiDang;

public class chitietbaidang_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_bai_dang);
        Bundle bundle = getIntent().getExtras();
        BaiDang baiDangItem=bundle.getParcelable("BaiDang");
        TextView txtTieuDe=findViewById(R.id.txtTieuDeChiTietBaiDang);
        txtTieuDe.setText(baiDangItem.getTieuDe());
        TextView txtDiaChi = findViewById(R.id.txtDiaChiChiTiet);
        txtDiaChi.setText("Địa chỉ: "+baiDangItem.getPhong().getDiaChi());
        TextView txtDienTich = findViewById(R.id.txtDienTichChiTiet);
        txtDienTich.setText("Diện tích: "+String.valueOf(baiDangItem.getPhong().getDienTich())+"m2");
        TextView txtThoiGianDang= findViewById(R.id.txtThoigian);
        txtThoiGianDang.setText("Thời gian đăng: "+baiDangItem.getThoiGianDang());
        TextView txtGia= findViewById(R.id.txtGiaPhong);
        txtGia.setText("Giá: "+setGia(baiDangItem.getPhong().getGia()));
    }
    private String setGia(int gia){
        if(gia>0&&gia<1000000)
            return String.valueOf(gia/1000+" Nghìn");
        if(gia>1000000&&gia<1000000000)
            return String.valueOf((gia/1000000)+" Triệu");
        if(gia>1000000000)
            return String.valueOf((gia/1000000000)+" Tỷ");
        else return String.valueOf(0);
    }
}
