package com.ictu.vusenpai.timtro.activity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.PhongAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.Update;

import java.util.ArrayList;

public class search_activity extends Fragment {
    ArrayList<BaiDang> lstBaiDangSearch;
    RecyclerView lvPhongSreach;
    SearchView sreachView;
    PhongAdapter adapter;
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
        lstBaiDangSearch.clear();
        lstBaiDangSearch= Update.getLsBaiDang();
        adapter = new PhongAdapter(getContext(),lstBaiDangSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvPhongSreach.setHasFixedSize(true);
        lvPhongSreach.setAdapter(adapter);
        lvPhongSreach.setLayoutManager(linearLayoutManager);
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
                    adapter = new PhongAdapter(getContext(),myList);
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