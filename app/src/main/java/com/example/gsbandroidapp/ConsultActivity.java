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
import com.google.android.gms.common.data.DataBuffer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("fiches");
    private ListView listViewFiches;
    private List<Fiche> ficheList;
    private Long maxId;
    private TextView textView1, textView2, textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        listViewFiches = findViewById(R.id.listViewFiches);
        textView1 = findViewById(R.id.textViewValue1);
        textView2 = findViewById(R.id.textViewValue2);
        textView3 = findViewById(R.id.textViewValue3);
        textView4 = findViewById(R.id.textViewValue4);
        ficheList = new ArrayList<>();
    }

        @Override
    protected void onStart() {
            super.onStart();

/*            mRootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    maxId = dataSnapshot.getChildrenCount();
                    Toast.makeText(ConsultActivity.this, String.valueOf(maxId), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


            });}*/

                mRootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
/*                       Integer maxIdInt = maxId.intValue();
                         Log.d("maxIdInt", String.valueOf(maxIdInt));*/

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Boolean condition = user.getEmail() == ds.child("ficheUserEmail").getValue();
                            if(condition == true) {
                                String value1 = ds.child("etp").getValue().toString();
                                String value2 = ds.child("km").getValue().toString();
                                String value3 = ds.child("nui").getValue().toString();
                                String value4 = ds.child("rep").getValue().toString();
                                textView1.setText(value1);
                                textView2.setText(value2);
                                textView3.setText(value3);
                                textView4.setText(value4);
                            }else{
                                Toast.makeText(ConsultActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
}