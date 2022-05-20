package com.example.projectfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.projectfinal.R;
import com.example.projectfinal.model.CovertBitmap;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
   // private int imgs[] ={R.drawable.img2,R.drawable.img3, R.drawable.img4,R.drawable.img2,R.drawable.img2,R.drawable.img2,R.drawable.img2,R.drawable.img2};
    Context context;
    private String usn;
    private List<ImageUser> list;
    private CovertBitmap  covertBitmap;

    public ImageAdapter(Context context,List<ImageUser> list) {
        this.context = context;
        this.list = list;
       // Dbsqlite dbsqlite = new Dbsqlite()
    }
    public ImageAdapter(Context context ) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.imge_item,parent,false);
            ImageView imageView = inflater.findViewById(R.id.imageItem);
            covertBitmap = new CovertBitmap();
            imageView.setImageBitmap(covertBitmap.StringToBitMap(list.get(position).getImg()));
            return inflater;
        }
        return convertView;
    }
}
