package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;
import java.util.List;

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


public class ConsultActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("fiches");
    private Long maxId;
    private TextView textView1, textView2, textView3, textView4, textView5;
    private Button btnPrevious, btnNext, btnMenu;
    private int currentKey = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        textView1 = findViewById(R.id.textViewValue1);
        textView2 = findViewById(R.id.textViewValue2);
        textView3 = findViewById(R.id.textViewValue3);
        textView4 = findViewById(R.id.textViewValue4);
        textView5 = findViewById(R.id.textViewValue5);
        btnNext = findViewById(R.id.btnNextFiche);
        btnPrevious = findViewById(R.id.btnPreviousFiche);
        btnMenu = findViewById(R.id.btnMenu);
    }

        @Override
    protected void onStart() {
            super.onStart();

            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent v = new Intent(ConsultActivity.this, MenuActivity.class);
                    startActivity(v);
                    finish();
                }
            });
            if(currentKey == -1) {
                currentKey = 1;
                btnPrevious.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                Log.d("KEY IS :" , String.valueOf(currentKey));
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();
                DatabaseReference newRef = mRootRef.child(userId);
                newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        maxId = dataSnapshot.getChildrenCount();
                        Integer maxIdInt = maxId.intValue();
                        Log.d("maxIdInt", String.valueOf(maxIdInt));

                        String key = String.valueOf(currentKey);
                        DataSnapshot ds = dataSnapshot.child(key);
                        String value1 = ds.child("etp").getValue().toString();
                        String value2 = ds.child("km").getValue().toString();
                        String value3 = ds.child("nui").getValue().toString();
                        String value4 = ds.child("rep").getValue().toString();
                        String value5 = ds.child("date").getValue().toString();
                        textView1.setText(value1);
                        textView2.setText(value2);
                        textView3.setText(value3);
                        textView4.setText(value4);
                        textView5.setText(value5);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int newKey = decreaseKey(currentKey);
                    currentKey = newKey;
                    if(currentKey == 1){btnPrevious.setVisibility(View.INVISIBLE);}
                    btnNext.setVisibility(View.VISIBLE);
                    Log.d("KEY IS :" , String.valueOf(newKey));
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = user.getUid();
                    DatabaseReference newRef = mRootRef.child(userId);
                    newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            maxId = dataSnapshot.getChildrenCount();
                            Integer maxIdInt = maxId.intValue();
                            Log.d("maxIdInt", String.valueOf(maxIdInt));
                            String key = String.valueOf(newKey);
                            DataSnapshot ds = dataSnapshot.child(key);
                            String value1 = ds.child("etp").getValue().toString();
                            String value2 = ds.child("km").getValue().toString();
                            String value3 = ds.child("nui").getValue().toString();
                            String value4 = ds.child("rep").getValue().toString();
                            String value5 = ds.child("date").getValue().toString();
                            textView1.setText(value1);
                            textView2.setText(value2);
                            textView3.setText(value3);
                            textView4.setText(value4);
                            textView5.setText(value5);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int newKey = increaseKey(currentKey);
                    currentKey = newKey;
                    if(currentKey == maxId){btnNext.setVisibility(View.INVISIBLE);}
                    btnPrevious.setVisibility(View.VISIBLE);
                    Log.d("KEY IS :", String.valueOf(newKey));
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = user.getUid();
                    DatabaseReference newRef = mRootRef.child(userId);
                    newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            maxId = dataSnapshot.getChildrenCount();
                            Integer maxIdInt = maxId.intValue();
                            Log.d("maxIdInt", String.valueOf(maxIdInt));
                            String key = String.valueOf(newKey);
                            DataSnapshot ds = dataSnapshot.child(key);
                            String value1 = ds.child("etp").getValue().toString();
                            String value2 = ds.child("km").getValue().toString();
                            String value3 = ds.child("nui").getValue().toString();
                            String value4 = ds.child("rep").getValue().toString();
                            String value5 = ds.child("date").getValue().toString();
                            textView1.setText(value1);
                            textView2.setText(value2);
                            textView3.setText(value3);
                            textView4.setText(value4);
                            textView5.setText(value5);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            });
        }
    public int increaseKey(int currentKey){ int newCurrentKey = currentKey + 1;return newCurrentKey;}
    public int decreaseKey(int currentKey){int newCurrentKey = currentKey - 1;return newCurrentKey;}
}