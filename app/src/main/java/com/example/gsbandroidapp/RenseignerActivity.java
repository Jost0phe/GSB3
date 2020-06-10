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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RenseignerActivity extends AppCompatActivity {

    private EditText valeur1, valeur2;
    private Button btnSave;
    private Long maxId;

    DatabaseReference databaseFiche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renseigner);
        valeur1 = findViewById(R.id.input_valeur1);
        valeur2 = findViewById(R.id.input_valeur2);
        btnSave = findViewById(R.id.btn_save);

        databaseFiche = FirebaseDatabase.getInstance().getReference().child("fiches");
        databaseFiche.addValueEventListener(new ValueEventListener() {
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
        String storedValue1 = valeur1.getText().toString().trim();
        String storedValue2 = valeur2.getText().toString().trim();

        if(!TextUtils.isEmpty(storedValue1)){
            String id = String.valueOf(maxId+1);
            Fiche fiche = new Fiche(storedValue1, storedValue2);
            databaseFiche.child(id).setValue(fiche);
            Toast.makeText(this,"Fiche added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Enter Values", Toast.LENGTH_LONG).show();
        }
    }

}