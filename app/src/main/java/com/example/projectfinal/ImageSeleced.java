package com.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectfinal.model.CovertBitmap;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;

public class ImageSeleced extends AppCompatActivity {

    private Dbsqlite dbsqlite;
    private ImageUser imageUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_seleced);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        imageUser = (ImageUser) intent.getSerializableExtra("imageuser");
         dbsqlite = new Dbsqlite(this);
        CovertBitmap covertBitmap = new CovertBitmap();
        //Toast.makeText(this, imageUser.getId()+"", Toast.LENGTH_SHORT).show();
        ImageView imageView = findViewById(R.id.imageSelected);
        imageView.setImageBitmap(covertBitmap.StringToBitMap(imageUser.getImg()));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteImageItem:
                if (dbsqlite.deleteImage(imageUser.getId())>0){
                    Toast.makeText(this, "Delete success", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image_selected,menu);
        return super.onCreateOptionsMenu(menu);
    }
}