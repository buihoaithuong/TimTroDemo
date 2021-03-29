package com.ictu.vusenpai.timtro.layouts;

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
import com.ictu.vusenpai.timtro.activity.activity_chitiet_baiDang_Creat;
import com.ictu.vusenpai.timtro.activity.chitietbaiDang_activity;
import com.ictu.vusenpai.timtro.adapter.RecyclerView_listBaiDangTongAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.xuly.Update;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;

import java.util.ArrayList;

public class list_baiDang_Fragment extends Fragment {
    private RecyclerView lvPhong;
    private RecyclerView_listBaiDangTongAdapter adapter;
    private FloatingActionButton btn ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_phong_layout,container,false);
        anhxa(view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_chitiet_baiDang_Creat.class);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvPhong.setHasFixedSize(true);
        lvPhong.setLayoutManager(linearLayoutManager);
        final ArrayList<BaiDang> lsBaiDang = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("baiDang")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Update.setLsBaiDang();
                        lsBaiDang.clear();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            lsBaiDang.add(child.getValue(BaiDang.class));
                        }
                        adapter=new RecyclerView_listBaiDangTongAdapter(getContext(),lsBaiDang);
                        adapter.setMyOnItemClickListener(new MyOnItemClickListener() {
                            @Override
                            public void onClick(BaiDang baiDang) {
                                Intent intent = new Intent(getActivity(), chitietbaiDang_activity.class);
                                intent.putExtra("Phong",baiDang);
                                startActivity(intent);
                            }
                        });
                        lvPhong.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return view;
    }
    private void anhxa(View view){
        lvPhong=view.findViewById(R.id.rvListPhong);
        btn = view.findViewById(R.id.btnCreateBaidang);
    }
}
