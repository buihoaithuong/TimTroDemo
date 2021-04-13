package com.ictu.vusenpai.timtro.activity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.ImageSliderAdapterInternet;
import com.ictu.vusenpai.timtro.model.BaiDang;

import org.json.JSONException;

import java.util.List;

public class chitietbaiDang_activity extends AppCompatActivity implements OnMapReadyCallback {
    TextView txtDiaChi,txtDienTich,txtGia,txtSDT;
    SupportMapFragment mapFragment;
    ViewPager imageSlider;
    GoogleMap map;
    ProgressDialog progressDialog ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietphong);
        anhxa();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        final BaiDang baiDangItem = bundle.getParcelable("Phong");
        ImageSliderAdapterInternet imageSliderAdapterInternet = new ImageSliderAdapterInternet(getApplicationContext(),baiDangItem.getAnhFeeback());
        getSupportActionBar().setTitle(String.valueOf(baiDangItem.getTieuDe()));
        imageSlider.setAdapter(imageSliderAdapterInternet);
        txtDiaChi.setText("Địa chỉ: " + baiDangItem.getDiaChi());
        txtDienTich.setText("Diện tích: " + String.valueOf(baiDangItem.getDienTich()) + "m2");
        txtGia.setText("Giá: " +baiDangItem.getGia());
        txtSDT.setText(baiDangItem.getSoDienThoai());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void anhxa(){
        txtDiaChi = findViewById(R.id.txtDiaChiChiTiet);
        txtDienTich = findViewById(R.id.txtDienTichChiTiet);
        txtGia= findViewById(R.id.txtGiaPhong);
        imageSlider = findViewById(R.id.imageSlider);
        txtSDT = findViewById(R.id.txtSoDienThoai);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frMapChiTiet);
        mapFragment.getMapAsync(this);
    }
    private void loadmap(){
        class LoadMap extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try {
                    LatLng address = getLocationFromAddress(getApplicationContext(),txtDiaChi.getText().toString() );
                    map.addMarker(new MarkerOptions().position(address).title("Phòng trọ ở đây"));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(address,16));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        LoadMap load = new LoadMap();
        load.execute();
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
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
        }
        return p1;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        loadmap();
    }
}
