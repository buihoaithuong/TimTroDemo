package com.ictu.vusenpai.timtro.activity;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.ImageSliderAdapterInternet;
import com.ictu.vusenpai.timtro.model.BaiDang;

import java.util.List;

public class chitietbaiDang_activity extends AppCompatActivity implements OnMapReadyCallback {
    TextView txtTieuDe,txtDiaChi,txtDienTich,txtGia,txtSDT;
    SupportMapFragment mapFragment;
    GoogleMap map;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_phong);
        anhxa();
        Bundle bundle = getIntent().getExtras();
        final BaiDang baiDangItem = bundle.getParcelable("Phong");
        final ViewPager imageSlider = findViewById(R.id.imageSlider);
        ImageSliderAdapterInternet imageSliderAdapterInternet = new ImageSliderAdapterInternet(baiDangItem.getAnhFeeback());
        imageSlider.setAdapter(imageSliderAdapterInternet);
        txtTieuDe.setText(baiDangItem.getTieuDe());
        txtDiaChi.setText("Địa chỉ: " + baiDangItem.getDiaChi());
        txtDienTich.setText("Diện tích: " + String.valueOf(baiDangItem.getDienTich()) + "m2");
        txtGia.setText("Giá: " + setGia(baiDangItem.getGia()));
        txtSDT.setText("07462522734");
        if(getLocationFromAddress(this,txtDiaChi.getText().toString())!=null)
            mapFragment.getMapAsync(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void anhxa(){
        txtTieuDe=findViewById(R.id.txtTieuDeChiTietBaiDang);
        txtDiaChi = findViewById(R.id.txtDiaChiChiTiet);
        txtDienTich = findViewById(R.id.txtDienTichChiTiet);
        txtGia= findViewById(R.id.txtGiaPhong);
        txtSDT = findViewById(R.id.txtSoDienThoai);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frMapChiTiet);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng address = getLocationFromAddress(this,txtDiaChi.getText().toString() );
        map.addMarker(new MarkerOptions().position(address).title("Phòng trọ ở đây"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(address,17));


    }
    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;
    }
}
