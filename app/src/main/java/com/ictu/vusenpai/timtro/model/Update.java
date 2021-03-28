package com.ictu.vusenpai.timtro.model;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ictu.vusenpai.timtro.activity.chitietbaiDang_activity;
import com.ictu.vusenpai.timtro.adapter.PhongAdapter;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;

import java.util.ArrayList;

public class Update {
    private static ArrayList<BaiDang> lsBaiDang = new ArrayList<>();

    public static ArrayList<BaiDang> getLsBaiDang() {
        return lsBaiDang;
    }

    public static void setLsBaiDang() {
        FirebaseDatabase.getInstance().getReference("baiDang")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (lsBaiDang.size()!=0)
                            lsBaiDang.clear();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            lsBaiDang.add(child.getValue(BaiDang.class));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}
