package com.ictu.vusenpai.timtro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.layouts.Login_Fragment;
import com.ictu.vusenpai.timtro.xuly.Update;
import com.ictu.vusenpai.timtro.xuly.Utils;

public class login_activity extends AppCompatActivity {
    private static FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Update.setlsUser(mAuth.getInstance().getCurrentUser().getUid());
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new Login_Fragment(), Utils.Login_Fragment).commit();
        }
        findViewById(R.id.close_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    protected void replaceLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, new Login_Fragment(),
                        Utils.Login_Fragment).commit();
    }
    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = getSupportFragmentManager().findFragmentByTag(Utils.SignUp_Fragment);
        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else
            finish();
    }
}