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

import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

public class RegisterName extends AppCompatActivity {

    Button btnRegister;
    private EditText name;
    private Dbsqlite dbsqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnRegister = findViewById(R.id.btnRegister);
        name = findViewById(R.id.nameRegister);
        Intent intent = getIntent();
        User user = (User)  intent.getSerializableExtra("user");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // System.out.println(user);
                if (name.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterName.this, "Please input name", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.setName(name.getText().toString());
                    dbsqlite= new Dbsqlite(view.getContext());
                    if (dbsqlite.insertUser(user) >0){
                        Toast.makeText(RegisterName.this, "Register success", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterName.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(view.getContext(), Login.class);
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