package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("fiches");
    private Long maxId;
    private TextView textUser, textDate, textCount;
    private Button btnMenu, btnConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        textUser = findViewById(R.id.textViewUser);
        textDate = findViewById(R.id.textViewDate);
        textCount = findViewById(R.id.textViewCount);
        btnMenu = findViewById(R.id.btnMenu);
        btnConsult = findViewById(R.id.btnConsult);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(ProfilActivity.this, MenuActivity.class);
                startActivity(v);
                finish();
            }
        });

        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(ProfilActivity.this, ConsultActivity.class);
                startActivity(v);
                finish();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userMail = user.getEmail();
        String userId = user.getUid();
        DatabaseReference newRef = mRootRef.child(userId);
        final String date = String.valueOf(user.getMetadata().getCreationTimestamp());
        newRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId = dataSnapshot.getChildrenCount();
                String maxIdString = maxId.toString();
                textUser.setText(userMail);
                textDate.setText(date);
                textCount.setText(maxIdString);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}