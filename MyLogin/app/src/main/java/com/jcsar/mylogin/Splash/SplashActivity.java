package com.jcsar.mylogin.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jcsar.mylogin.Activities.LoginActivity;
import com.jcsar.mylogin.Activities.MainActivity;
import com.jcsar.mylogin.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this,LoginActivity.class);
        Intent intentMain = new Intent(this,MainActivity.class);

        if( !TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) &&
                !TextUtils.isEmpty(Util.getPassMailPrefs(prefs)) ){
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }
        //para no volver con btn atras
        finish();
    }

}
