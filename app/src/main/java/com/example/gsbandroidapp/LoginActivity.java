package com.example.gsbandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btn1;
    private EditText edt_log, edt_pwd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

        btn1 = findViewById(R.id.btn_login);
        edt_log = findViewById(R.id.input_mail);
        edt_pwd = findViewById(R.id.input_password);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_log = edt_log.getText().toString();
                String s_pwd = edt_pwd.getText().toString();

                mAuth.signInWithEmailAndPassword(s_log, s_pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent s = new Intent(LoginActivity.this,MenuActivity.class);
                            startActivity(s);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Echec", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}