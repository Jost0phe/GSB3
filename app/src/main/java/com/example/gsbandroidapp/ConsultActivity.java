package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsbandroidapp.ui.Fiche;
import com.example.gsbandroidapp.ui.FicheList;
import com.google.android.gms.common.data.DataBuffer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("fiches");
    private ListView listViewFiches;
    private List<Fiche> ficheList;
    private Long maxId;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        listViewFiches = findViewById(R.id.listViewFiches);
        textView1 = findViewById(R.id.textViewValue1);
        textView2 = findViewById(R.id.textViewValue2);
        ficheList = new ArrayList<>();
    }

        @Override
    protected void onStart() {
            super.onStart();

            mRootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    maxId = dataSnapshot.getChildrenCount();
                    Toast.makeText(ConsultActivity.this, String.valueOf(maxId), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

                mRootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer maxIdInt = maxId.intValue();
                        Log.d("maxIdInt", String.valueOf(maxIdInt));
                        for (int i = 1; i <= maxIdInt; i++) {
                            String value1 = dataSnapshot.child(String.valueOf(i)).child("valeur1").getValue().toString();
                            String value2 = dataSnapshot.child(String.valueOf(i)).child("valeur2").getValue().toString();
                            Toast.makeText(ConsultActivity.this, value1, Toast.LENGTH_SHORT).show();
                            textView1.setText(value1);
                            textView2.setText(value2);

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
}