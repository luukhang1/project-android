package com.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

public class Register extends AppCompatActivity {

    Button btnNext;
    private User user;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnNext = findViewById(R.id.btnNextRegister);
        username = findViewById(R.id.usernameRegister);
        password = findViewById(R.id.passRegister);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(Register.this, username.getText().toString()+"hshhh", Toast.LENGTH_SHORT).show();
                if (username.getText().toString().trim().isEmpty()  || password.getText().toString().trim().isEmpty()){
                    Toast.makeText(Register.this, "Please input full info", Toast.LENGTH_SHORT).show();
                } else {
                    user = new User(username.getText().toString(),password.getText().toString());
                    Intent intent = new Intent(view.getContext(),RegisterName.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       finish();
        return true;
    }
}