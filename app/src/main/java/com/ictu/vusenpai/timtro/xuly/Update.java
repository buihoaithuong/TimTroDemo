package com.ictu.vusenpai.timtro.xuly;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ictu.vusenpai.timtro.adapter.RecyclerView_listBaiDangCaNhanAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.User;

import java.util.ArrayList;

public class Update {
    private static ArrayList<BaiDang> lsBaiDangTong = new ArrayList<>();
    private static User Usser;
    private static ArrayList<BaiDang> lsBaiDangCaNhan = new ArrayList<>();

    public static ArrayList<BaiDang> getLsBaiDangCaNhan() {
        return lsBaiDangCaNhan;
    }

    public static void setLsBaiDangCaNhan() {
        Query query = FirebaseDatabase.getInstance().getReference("baiDang")
                .orderByChild("idUser")
                .equalTo(Usser.getId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lsBaiDangCaNhan.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot item : snapshot.getChildren()) {
                        BaiDang artist = item.getValue(BaiDang.class);
                        lsBaiDangCaNhan.add(artist);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static ArrayList<BaiDang> getLsBaiDang() {
        return lsBaiDangTong;
    }
    public static User getLsUsser(){
        return Usser;
    }
    public static void setLsBaiDang() {
        FirebaseDatabase.getInstance().getReference("baiDang")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lsBaiDangTong.clear();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            lsBaiDangTong.add(child.getValue(BaiDang.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
    public static void setlsUser(String uid){
        FirebaseDatabase.getInstance().getReference("Users").child(uid)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usser = snapshot.getValue(User.class);
                setLsBaiDangCaNhan();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static void removeBaiDang(String idBaiDang, ArrayList<String> lsUrl){
        for (String item:lsUrl)
            FirebaseStorage.getInstance().getReferenceFromUrl(item).delete();
        FirebaseDatabase.getInstance().getReference("baiDang").child(idBaiDang).removeValue();
    }
}
