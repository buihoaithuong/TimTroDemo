package com.ictu.vusenpai.timtro.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.PhongAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.Update;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;

import java.util.ArrayList;

public class list_baiDang_activity extends Fragment {
    ArrayList<BaiDang> listBaiDang;
    RecyclerView lvPhong;
    PhongAdapter adapter;
    FloatingActionButton btn ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_phong_layout,container,false);
        anhxa(view);
        listBaiDang.clear();
        listBaiDang=Update.getLsBaiDang();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_chitiet_baiDang_chinhsua.class);
                startActivity(intent);
            }
        });
        Toast.makeText(getContext(), String.valueOf(Update.getLsBaiDang().size()), Toast.LENGTH_SHORT).show();
        adapter=new PhongAdapter(getContext(),listBaiDang);
        adapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onClick(BaiDang baiDang) {
                Intent intent = new Intent(getActivity(), chitietbaiDang_activity.class);
                intent.putExtra("Phong",baiDang);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvPhong.setHasFixedSize(true);
        lvPhong.setAdapter(adapter);
        lvPhong.setLayoutManager(linearLayoutManager);
        return view;
    }
    private void anhxa(View view){
        lvPhong=view.findViewById(R.id.rvListPhong);
        btn = view.findViewById(R.id.btnCreateBaidang);
        listBaiDang = new ArrayList<>();
    }
}
