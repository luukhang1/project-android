package com.example.projectfinal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectfinal.ImageSeleced;
import com.example.projectfinal.R;
import com.example.projectfinal.adapter.ImageAdapter;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private User user;
    private Dbsqlite dbsqlite;
    private List<ImageUser> list;
    private  ImageView searchIm;
    private EditText text;
    private List<ImageUser> listSearch;
    private  ImageAdapter imageView;
    private  GridView gridView;
    public FragmentSearch(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        gridView = view.findViewById(R.id.girlSearch);
        dbsqlite = new Dbsqlite(getContext());
        list = new ArrayList<>();
        list = dbsqlite.getAllImageUser(user.getUsername());

        searchIm = view.findViewById(R.id.imageSearch);
        text = view.findViewById(R.id.search);
        searchIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textSeach = text.getText().toString();
                listSearch = dbsqlite.filterImageByName(textSeach);
                imageView = new ImageAdapter(view.getContext(),listSearch);
                imageView.notifyDataSetChanged();
                gridView.setAdapter(imageView);
                Toast.makeText(getContext(), "Search success", Toast.LENGTH_SHORT).show();
                text.setText("");

            }
        });

        imageView = new ImageAdapter(view.getContext(),list);
        imageView.notifyDataSetChanged();
        gridView.setAdapter(imageView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ImageSeleced.class);
                ImageUser user = list.get(i);
                intent.putExtra("imageuser",user);
                startActivity(intent);
            }
        });
        return  view;

    }

    @Override
    public void onResume() {
        super.onResume();
        list =dbsqlite.getAllImageUser(user.getUsername());
    }
}
