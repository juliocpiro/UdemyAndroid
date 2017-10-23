package com.jcsar.mylogin.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.jcsar.mylogin.R;
import com.jcsar.mylogin.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemenber;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email, password)) {
                    gotoMain();
                    safeOnPreferences(email, password);
                }
            }
        });
    }

    private void bindUI() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemenber = (Switch) findViewById(R.id.switchRemenber);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void setCredentialsIfExist() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getPassMailPrefs(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "password is not valid, 4 characers or more, please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void safeOnPreferences(String email, String password) {
        if (switchRemenber.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("pass", password);
            editor.commit();
            editor.apply();
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() > 4;
    }

    private void gotoMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
