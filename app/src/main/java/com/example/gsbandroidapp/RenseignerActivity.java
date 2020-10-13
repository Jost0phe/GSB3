package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gsbandroidapp.ui.Fiche;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RenseignerActivity extends AppCompatActivity {

    private EditText etp,km,nui,rep,date;
    private Button btnSave;
    private Long maxId;

    DatabaseReference databaseFiche = FirebaseDatabase.getInstance().getReference("fiches");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renseigner);
        etp = findViewById(R.id.input_etp);
        km = findViewById(R.id.input_km);
        nui = findViewById(R.id.input_nui);
        rep = findViewById(R.id.input_rep);
        date = findViewById(R.id.input_date);
        btnSave = findViewById(R.id.btn_save);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String ficheUserId = user.getUid();

        DatabaseReference newRef = databaseFiche.child(ficheUserId);;
        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxId = dataSnapshot.getChildrenCount();
                }else{
                    maxId = (long)0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFiche();
                Intent s = new Intent(RenseignerActivity.this, ConsultActivity.class);
                startActivity(s);
                finish();
            }
        });
    }

    private void addFiche(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String ficheUserEmail = user.getEmail();
        String ficheUserId = user.getUid();
        String storedEtp = etp.getText().toString().trim();
        String storedKm = km.getText().toString().trim();
        String storedNui = nui.getText().toString().trim();
        String storedRep = rep.getText().toString().trim();
        String storedDate = date.getText().toString().trim();
        DatabaseReference functionRef = databaseFiche.child(ficheUserId);

        if(!TextUtils.isEmpty(storedEtp)){
            String id = String.valueOf(maxId+1);
            Fiche fiche = new Fiche(id,storedEtp,storedKm,storedNui,storedRep,storedDate);
            functionRef.child(id).setValue(fiche);
            Toast.makeText(this,"Fiche added, USEREMAIL = "+ ficheUserEmail.trim(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Enter values ", Toast.LENGTH_LONG).show();
        }
    }
}