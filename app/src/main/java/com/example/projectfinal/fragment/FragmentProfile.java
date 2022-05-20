package com.example.projectfinal.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.projectfinal.Login;
import com.example.projectfinal.R;
import com.example.projectfinal.model.CovertBitmap;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.User;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentProfile extends Fragment {
    private static final int MY_REQUEST_CODE = 10;

    private User user;
    public FragmentProfile(User user) {
        this.user = user;
    }
    Dbsqlite dbsqlite;
    Uri mUri;
    private CircleImageView imageView;
    EditText name,usename,pass;
    Bitmap bitmap = null;

    private ActivityResultLauncher<Intent> mActionSelectImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //Toast.makeText(getContext(), "noooo", Toast.LENGTH_SHORT).show();
            if (result.getResultCode()
                    == Activity.RESULT_OK){
                Intent data = result.getData();
                if (data == null){
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = data.getData();
                mUri = uri;
               // Toast.makeText(getContext(), ""+uri, Toast.LENGTH_SHORT).show();
                try {
                    Bitmap b = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                   bitmap = b;
                    imageView.setImageBitmap(b);
                   // bitmap = imageView.getDrawingCache();
                   CovertBitmap covertBitmap = new CovertBitmap();
                   bitmap = covertBitmap.perform(bitmap);
                  user.setImg(covertBitmap.BitMapToString(bitmap));
                   // Toast.makeText(getContext(), ""+b, Toast.LENGTH_SHORT).show();


                } catch (IOException e) {
                    e.printStackTrace();
                }

//                if (uri == null){
//                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    try {
//                        Bitmap b = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//                        imageView.setImageBitmap(b);
//                        bitmap = b;
//
//                        //  user = new User(1,"ngoc","ngoc",bitmap);
////                    if (bitmap.)
//
//                    } catch (IOException e) {
//                        Toast.makeText(getContext(), "Khong the truy cap", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                }
              //  Log.d(m, "onActivityResult: ");



            }
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
       // GridView gridView = view.findViewById(R.id.girdViewProfile);
      //  ImageAdapter imageAdapter = new ImageAdapter(view.getContext());
     //   gridView.setAdapter(imageAdapter);
           dbsqlite =new Dbsqlite(getContext());
//       // User user = new User();
         imageView = view.findViewById(R.id.imgProfile);
       // Toast.makeText(getContext(), ""+ user.getUsername(), Toast.LENGTH_SHORT).show();
          user = dbsqlite.getUser(user.getUsername()+"");
         if (user ==null) Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
        CovertBitmap covertBitmap = new CovertBitmap();
        if (user != null && user.getImg() != null){

          //  System.out.println(user.getImg());
            Bitmap bitmap = covertBitmap.StringToBitMap(user.getImg());
           // bitmap = covertBitmap.perform(bitmap);
           imageView.setImageBitmap(bitmap);
        }
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(view.getContext(), ImageSeleced.class);
//                startActivity(intent);
//            }
//        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission(view);
            }
        });
        Button button = view.findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        });
        name = view.findViewById(R.id.nameProfile);
        usename = view.findViewById(R.id.usernameProfile);
        pass = view.findViewById(R.id.passProfile);
        name.setText( user.getName());
        usename.setText(user.getUsername());
        pass.setText(user.getPassword());
        Button button1 = view.findViewById(R.id.saveprofile);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim() == ""|| usename.getText().toString().trim()==""||pass.getText().toString().trim()==""){
                    Toast.makeText(getContext(), "Please inform full " +bitmap, Toast.LENGTH_SHORT).show();
                } else {

                    user.setName(name.getText().toString());
                    user.setUsername(usename.getText().toString());
                    user.setPassword(pass.getText().toString());
                    if (dbsqlite.updateUser(user) >0){

                        Toast.makeText(getContext(), "Save success", Toast.LENGTH_LONG).show();

                    };


                }
            }
        });

        return view;
    }
    private void checkpermission(View view) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGarally();
            return;
        }
        if( ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

            openGarally();
        } else {
            String pemissionp[] ={Manifest.permission.READ_EXTERNAL_STORAGE};

            requestPermissions(pemissionp,MY_REQUEST_CODE);
        }
    }
    private void openGarally() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActionSelectImage.launch(Intent.createChooser(intent,"select image"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE){
            if (grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGarally();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
