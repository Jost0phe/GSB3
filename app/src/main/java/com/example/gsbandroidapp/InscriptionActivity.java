package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InscriptionActivity extends AppCompatActivity {

    private Button btn_signin;
    private EditText edt_log, edt_pwd;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

        edt_log = findViewById(R.id.input_mail);
        edt_pwd = findViewById(R.id.input_pwd);
        btn_signin = findViewById(R.id.btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_log = edt_log.getText().toString();
                String  s_pwd = edt_pwd.getText().toString();

                if(s_pwd.length()< 6){
                    Toast.makeText(InscriptionActivity.this,"Mot de passe trop court", Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(s_log, s_pwd).addOnCompleteListener(InscriptionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(InscriptionActivity.this,"Enregistré",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.w("firgsb",task.getException());
                            Toast.makeText(InscriptionActivity.this,"Echec", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}