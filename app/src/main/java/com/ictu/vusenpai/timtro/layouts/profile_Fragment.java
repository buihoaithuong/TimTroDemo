package com.ictu.vusenpai.timtro.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.model.User;
import com.ictu.vusenpai.timtro.xuly.Update;

public class profile_Fragment extends Fragment {
    TextView tv_fullname,tv_add,tv_email,tv_diachiCT,tv_SDT;
    User user;
    private static FirebaseAuth mAuth;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile_layout,container,false);
        anhxa(view);
        loadProfile();
        return view;
    }
    private void anhxa(View view){
        tv_add = view.findViewById(R.id.tv_addessr);
        tv_diachiCT = view.findViewById(R.id.tv_fullDiachi);
        tv_email = view.findViewById(R.id.tv_email);
        tv_fullname = view.findViewById(R.id.tv_name);
        tv_SDT = view.findViewById(R.id.tv_sdt);
        mAuth = FirebaseAuth.getInstance();
    }
    private void loadProfile(){
        tv_add.setText(Update.getLsUsser().getDiaChi());
        tv_diachiCT.setText(Update.getLsUsser().getDiaChi());
        tv_email.setText(Update.getLsUsser().getEmail());
        tv_fullname.setText(Update.getLsUsser().getHo_ten());
        tv_SDT.setText(Update.getLsUsser().getSDT());
    }
}