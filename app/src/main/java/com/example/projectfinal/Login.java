package com.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

public class Login extends AppCompatActivity {
    Button buttonLogin;
    private User user;
    private EditText editText1,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.loginLogin);
        editText1 = findViewById(R.id.usernameLogin);
        editText2  = findViewById(R.id.passLogin);
        Dbsqlite dbsqlite = new Dbsqlite(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(editText1.getText().toString(),editText2.getText().toString());
                if (dbsqlite.CheckLogin(user) >0){
                    user = dbsqlite.getUser(user.getUsername());
                    if (user != null) {
                        Toast.makeText(Login.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(),MainActivity.class);
                        intent.putExtra("usn",user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Login.this, "username or password not found", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(view.getContext(),MainActivity.class);
//                startActivity(intent);
            }
        });
        Button btnRegister = findViewById(R.id.btnRegisterMain);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),Register.class);
                startActivity(intent);
            }
        });
    }




}