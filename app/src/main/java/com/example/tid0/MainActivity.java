package com.example.tid0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText divId;
    EditText pw;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divId = findViewById(R.id.div_id);
        pw = findViewById(R.id.pw);

        btnLogin = findViewById(R.id.btn_login);


    }



}