package com.example.gsbandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

        btn1 = findViewById(R.id.btn_renseignerNew);
        btn2 = findViewById(R.id.btn_consult);
        btn3 = findViewById(R.id.btn_logOff);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MenuActivity.this,RenseignerActivity.class);
                startActivity(s);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MenuActivity.this,ConsultActivity.class);
                startActivity(s);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent s = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(s);
                finish();
            }
        });
    }
}