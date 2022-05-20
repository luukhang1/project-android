package com.example.projectfinal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.projectfinal.AllImages;
import com.example.projectfinal.ImageSeleced;
import com.example.projectfinal.R;
import com.example.projectfinal.adapter.ImageAdapter;
import com.example.projectfinal.R;
import com.example.projectfinal.adapter.ImageAdapter;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;


import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private int imgs[] = {R.drawable.a1,R.drawable.a4,R.drawable.a3,R.drawable.download};
    GridView gridView;
    private User user;
    private List<ImageUser> imageUsers;
    private Dbsqlite dbsqlite;
    public HomeFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        ImageSlider imageSlider  = view.findViewById(R.id.imageSlider);
        List<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel(imgs[0], ScaleTypes.FIT));
        list.add(new SlideModel(imgs[1], ScaleTypes.FIT));
        list.add(new SlideModel(imgs[2], ScaleTypes.FIT));
        imageSlider.setImageList(list, ScaleTypes.CENTER_INSIDE);
        //imageSlider.startSliding(300);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(view.getContext(), "show " + i, Toast.LENGTH_SHORT).show();
            }
        });
        dbsqlite = new Dbsqlite(getContext());
        imageUsers = dbsqlite.getAllImageUser(user.getUsername());

        gridView = view.findViewById(R.id.girlView);
        ImageAdapter imageAdapter = new ImageAdapter(view.getContext(),imageUsers);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageUser imageUser = imageUsers.get(position);
                Intent intent = new Intent(getContext(), ImageSeleced.class);
                intent.putExtra("imageuser",imageUser);
                startActivity(intent);
                //Toast.makeText(view.getContext(), "jhshshs" + position, Toast.LENGTH_SHORT).show();
            }
        });
        TextView textView = view.findViewById(R.id.textShowAll);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllImages.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        return view ;
    }
}
