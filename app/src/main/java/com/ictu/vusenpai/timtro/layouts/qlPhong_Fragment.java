package com.ictu.vusenpai.timtro.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.activity.chitietbaiDang_activity;
import com.ictu.vusenpai.timtro.adapter.RecyclerView_listBaiDangCaNhanAdapter;
import com.ictu.vusenpai.timtro.adapter.RecyclerView_listBaiDangTongAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.User;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;
import com.ictu.vusenpai.timtro.xuly.Update;

import java.util.ArrayList;

public class qlPhong_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<BaiDang> listBaidang;
    private RecyclerView_listBaiDangCaNhanAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.qlphong_layout,container,false);
        anhxa(view);
        adapter=new RecyclerView_listBaiDangCaNhanAdapter(getContext(),Update.getLsBaiDangCaNhan());
        adapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onClick(BaiDang baiDang) {
                Intent intent = new Intent(getActivity(), chitietbaiDang_activity.class);
                intent.putExtra("Phong",baiDang);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                adapter.remove(item.getGroupId());
                Toast.makeText(getActivity(), "Xoá bài thành công", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void anhxa(View view){
        recyclerView = view.findViewById(R.id.rvQlPhong);
        listBaidang = new ArrayList<>();
    }
}
