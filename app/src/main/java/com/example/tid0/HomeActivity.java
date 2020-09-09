package com.example.tid0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView transfert;
    CardView reception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        transfert = findViewById(R.id.transfer);
        reception = findViewById(R.id.reception);

        transfert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfer = new Intent(HomeActivity.this, ScanActivity.class);
                startActivity(transfer);
            }
        });

        reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recep = new Intent(HomeActivity.this, ReceptionActivity.class);
                startActivity(recep);
            }
        });

    }
}