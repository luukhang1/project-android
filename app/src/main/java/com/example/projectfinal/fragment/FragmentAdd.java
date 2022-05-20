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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.projectfinal.R;
import com.example.projectfinal.model.CovertBitmap;
import com.example.projectfinal.model.Dbsqlite;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentAdd extends Fragment {
    private static final int MY_REQUEST_CODE = 10;
    private ImageView imageView,imageView2;
    private Button button;
    private Uri mUri;
    private User user;
    private ImageUser imageUser;
    private EditText editText;
    private Bitmap bitmap = null;
    Dbsqlite dbsqlite;
    public FragmentAdd(User user) {
        this.user = user;
    }
    
    private ActivityResultLauncher<Intent> mActionSelectImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()
            == Activity.RESULT_OK){
                Intent data = result.getData();
                if (data == null){
                    return;
                }
                Uri uri = data.getData();
                 mUri = uri;
                try {
                    Bitmap b = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUri);
                 //   Toast.makeText(getContext(), ""+b, Toast.LENGTH_LONG).show();
                    imageView.setImageBitmap(b);
                    bitmap = b;

                  //  user = new User(1,"ngoc","ngoc",bitmap);
//                    if (bitmap.)

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        View view  = inflater.inflate(R.layout.fragment_add,container,false);
       // user = dbsqlite.getUser(user.getUsername());
        imageUser = new ImageUser();
        imageUser.setUsername(user.getUsername());
       // imageUser.set
        button = view.findViewById(R.id.addImage);
        imageView = view.findViewById(R.id.imageAdd);
        //imageView2 = view.findViewById(R.id.imageAdd1);
        editText = view.findViewById(R.id.imageName);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermission(view);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmap == null){
                    Toast.makeText(getContext(), "Please choose image", Toast.LENGTH_SHORT).show();
                } else{


                long size = bitmap.getRowBytes()* bitmap.getHeight();

                    CovertBitmap covertBitmap = new CovertBitmap();
                    bitmap = covertBitmap.perform(bitmap);
                    imageUser.setImg(covertBitmap.BitMapToString(bitmap));
                    imageUser.setName(editText.getText().toString());
                    Date date = new Date();
                    SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    imageUser.setDate(simpleDateFormat.format(date));
                   // Toast.makeText(getContext(), imageUser.getId()+"", Toast.LENGTH_SHORT).show();
                    dbsqlite = new Dbsqlite(getView().getContext());
                   if (dbsqlite.insertImageUser(imageUser)>0){
                       imageView.setImageResource(R.drawable.ic_baseline_image_search_24);
                       editText.setText("");
                       Toast.makeText(getContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                   }


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
        if( ContextCompat.checkSelfPermission(view.getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

            openGarally();
        } else {
            String pemissionp[] ={Manifest.permission.READ_EXTERNAL_STORAGE};

            requestPermissions(pemissionp,MY_REQUEST_CODE);
        }
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

    private void openGarally() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActionSelectImage.launch(Intent.createChooser(intent,"select image"));
    }
    
}
