package com.example.gsbandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gsbandroidapp.ui.Fiche;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RenseignerActivity extends AppCompatActivity {

    private EditText valeur1, valeur2;
    private Button btnSave;

    DatabaseReference databaseFiche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renseigner);
        valeur1 = findViewById(R.id.input_valeur1);
        valeur2 = findViewById(R.id.input_valeur2);
        btnSave = findViewById(R.id.btn_save);

        databaseFiche = FirebaseDatabase.getInstance().getReference();

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
            String id = databaseFiche.push().getKey();
            Fiche fiche = new Fiche(id, storedValue1, storedValue2);
            databaseFiche.child(id).setValue(fiche);
            Toast.makeText(this,"Fiche added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Enter Values", Toast.LENGTH_LONG).show();
        }
    }

}