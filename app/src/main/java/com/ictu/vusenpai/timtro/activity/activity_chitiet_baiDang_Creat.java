package com.ictu.vusenpai.timtro.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.ImageSliderAdapterLoca;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.User;
import com.ictu.vusenpai.timtro.xuly.Update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class activity_chitiet_baiDang_Creat extends AppCompatActivity {
    private EditText edtieuDe, edDiaChi, edDienTich,edSDT,edGia;
    private FloatingActionButton btnCreate,btnRemove, btnAddImg;
    private ViewPager imageSlider;
    private  ArrayList<Uri> listUri;
    private ProgressDialog progressDialog;
    private static final int PICK_IMAGES_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_phong_chinhsua);
        anhxa();
        //Tạo bài đăng
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new dangBai(listUri).execute();
            }
        });
        //Huỷ tạo bài đăng
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_chitiet_baiDang_Creat.this,MainActivity.class));
            }
        });
        //Thêm ảnh
        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void pickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if(requestCode==PICK_IMAGES_CODE){
                if(resultCode== Activity.RESULT_OK){
                    if(data.getClipData()!=null){
                        int cout = data.getClipData().getItemCount();
                        for (int i=0;i<cout;i++){
                            listUri.add(data.getClipData().getItemAt(i).getUri());
                        }
                        //xử lý cho ảnh ra
                        ImageSliderAdapterLoca imageSliderAdapter = new ImageSliderAdapterLoca(listUri);
                        imageSlider.setAdapter(imageSliderAdapter);
                    }
                    else {
                        listUri.add(data.getData());
                        //xử lý ảnh ra
                        ImageSliderAdapterLoca imageSliderAdapter = new ImageSliderAdapterLoca(listUri);
                        imageSlider.setAdapter(imageSliderAdapter);
                    }
                }
            }
        }
    }
    private void anhxa(){
        edDiaChi = findViewById(R.id.edDiaChiChiTiet);
        edDienTich=findViewById(R.id.edDienTichChiTiet);
        edGia=findViewById(R.id.edGiaPhongChiTiet);
        edSDT=findViewById(R.id.edSoDienThoaiChiTiet);
        btnCreate=findViewById(R.id.btnCreateNew);
        btnRemove=findViewById(R.id.btnXoaNew);
        edtieuDe = findViewById(R.id.edTieuDeChiTietBaiDangChinhSua);
        btnAddImg=findViewById(R.id.btnAddImg);
        imageSlider = findViewById(R.id.imageSliderChiTietChinhSua);
        listUri = new ArrayList<>();
    }
    private class dangBai extends AsyncTask<Void,Void,Void>{
        private ArrayList<Uri> lsUri;
        public dangBai(ArrayList<Uri> lsUri) {
            this.lsUri = lsUri;
        }
        private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        @Override
        protected Void doInBackground(Void... uris) {
            final ArrayList<String> lsUrl = new ArrayList<>();
            final String idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference imageFolder = FirebaseStorage.getInstance().getReference().child("ImageForder");
            for(Uri item : lsUri){
                final StorageReference imageName = imageFolder.child("Image"+item.getLastPathSegment());
                imageName.putFile(item).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                lsUrl.add(String.valueOf(uri));
                                if(lsUrl.size()==lsUri.size()){
                                    final String keyBaiDang = FirebaseDatabase.getInstance().getReference("baiDang").push().getKey();
                                    String time = df.format(Calendar.getInstance().getTime());
                                    BaiDang baidang = new BaiDang(edtieuDe.getText().toString(),edDiaChi.getText().toString(),Integer.parseInt(edDienTich.getText().toString()),lsUrl,Integer.parseInt(edGia.getText().toString()),keyBaiDang,idUser,time);
                                    FirebaseDatabase.getInstance().getReference("baiDang").child(keyBaiDang).setValue(baidang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            startActivity(new Intent(activity_chitiet_baiDang_Creat.this,MainActivity.class));
                                            Toast.makeText(getApplicationContext(), "Đăng bài thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(activity_chitiet_baiDang_Creat.this,"","Đang đăng bài....");
        }

        @Override
        protected void onPostExecute(Void strings) {
            super.onPostExecute(strings);
        }
    }
}