package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gsbandroidapp.ui.Fiche;
import com.example.gsbandroidapp.ui.FicheList;
import com.google.android.gms.common.data.DataBuffer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference fichesRef = mRootRef.child("fiche");
    private ListView listViewFiches;
    private List<Fiche> ficheList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        listViewFiches = findViewById(R.id.listViewFiches);
        ficheList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fichesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ficheList.clear();
                for(DataSnapshot ficheSnapshot : dataSnapshot.getChildren()){
                    Fiche fiche = ficheSnapshot.getValue(Fiche.class);

                    ficheList.add(fiche);
                }

                FicheList adapter = new FicheList(ConsultActivity.this, ficheList);
                listViewFiches.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}