package com.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.projectfinal.adapter.ImageAdapter;
import com.example.projectfinal.model.CovertBitmap;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

import java.util.List;

public class AllImages extends AppCompatActivity {

    GridView gridView;
    private Dbsqlite dbsqlite;
    private CovertBitmap covertBitmap;
    private User user;
    private List<ImageUser> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_images);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        dbsqlite = new Dbsqlite(this);
        list = dbsqlite.getAllImageUser(user.getUsername());
        gridView= findViewById(R.id.allImages);
        ImageAdapter imageAdapter = new ImageAdapter(this,list);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageUser imageUser = list.get(i);
                Intent intent1 = new Intent(view.getContext(),ImageSeleced.class);
                intent1.putExtra("imageuser", imageUser);
                Toast.makeText(AllImages.this, imageUser.getName(), Toast.LENGTH_LONG).show();
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}