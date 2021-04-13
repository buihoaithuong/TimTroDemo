package com.ictu.vusenpai.timtro.layouts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.activity.chitietbaiDang_activity;
import com.ictu.vusenpai.timtro.adapter.RecyclerView_listBaiDangTongAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;
import com.ictu.vusenpai.timtro.xuly.Update;

import java.util.ArrayList;

public class search_Fragment extends Fragment {
    ArrayList<BaiDang> lstBaiDangSearch;
    RecyclerView lvPhongSreach;
    SearchView sreachView;
    RecyclerView_listBaiDangTongAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_layout,container,false);
        anhxa(view);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvPhongSreach.setHasFixedSize(true);
        lvPhongSreach.setLayoutManager(linearLayoutManager);
        FirebaseDatabase.getInstance().getReference("baiDang")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Update.setLsBaiDang();
                        lstBaiDangSearch.clear();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            lstBaiDangSearch.add(child.getValue(BaiDang.class));
                        }
                        adapter=new RecyclerView_listBaiDangTongAdapter(getContext(),lstBaiDangSearch);
                        adapter.setMyOnItemClickListener(new MyOnItemClickListener() {
                            @Override
                            public void onClick(BaiDang baiDang) {
                                Intent intent = new Intent(getActivity(), chitietbaiDang_activity.class);
                                intent.putExtra("Phong",baiDang);
                                startActivity(intent);
                            }
                        });
                        lvPhongSreach.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        if(sreachView != null){
            sreachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }

                private void search(String newText) {
                    ArrayList<BaiDang> myList = new ArrayList<>();
                    for(BaiDang item : lstBaiDangSearch){
                        if(item.getDiaChi().toLowerCase().contains(newText.toLowerCase())||
                                String.valueOf(item.getDienTich()).toLowerCase().contains(newText.toLowerCase())||
                                String.valueOf(item.getGia()).toLowerCase().contains(newText.toLowerCase())||
                                item.getTieuDe().toLowerCase().contains(newText.toLowerCase())){
                            myList.add(item);
                        }
                    }
                    adapter = new RecyclerView_listBaiDangTongAdapter(getContext(),myList);
                    adapter.setMyOnItemClickListener(new MyOnItemClickListener() {
                        @Override
                        public void onClick(BaiDang baiDang) {
                            Intent intent = new Intent(getActivity(), chitietbaiDang_activity.class);
                            intent.putExtra("Phong",baiDang);
                            startActivity(intent);
                        }
                    });
                    lvPhongSreach.setAdapter(adapter);
                }
            });
        }
    }

    private void anhxa(View view){
        lvPhongSreach=view.findViewById(R.id.rvSearch);
        sreachView = view.findViewById(R.id.searchview);
        lstBaiDangSearch = new ArrayList<>();
    }

}