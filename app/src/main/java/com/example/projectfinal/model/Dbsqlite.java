package com.example.projectfinal.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbsqlite extends SQLiteOpenHelper {
    private   static final String DB_NAME = "db.project";
    private static final int VERSION = 5;
    public static  final  String DB_USER_IMAGE = "CREATE TABLE image(id INTEGER PRIMARY KEY AUTOINCREMENT," + "username TEXT,"+ "image TEXT, date TEXT, name TEXT)";
    public static  final  String DB_USER =  " CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT," +"username TEXT,"+ "password TEXT,"+ "name TEXT,"+ "avatar TEXT)";
    public Dbsqlite(@Nullable Context context) {
        super(context, DB_NAME, null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DB_USER_IMAGE);
        sqLiteDatabase.execSQL(DB_USER);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS image");
        onCreate(sqLiteDatabase);

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public long insertUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("password",user.getPassword());
        contentValues.put("name",user.getName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("user",null,contentValues);

    }
    public long CheckLogin(User user){
       SQLiteDatabase sqLiteDatabase= getReadableDatabase();
       String arg[] = {user.getUsername(), user.getPassword()};
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM user WHERE username = ? AND password = ?",arg);
        if (cursor.getCount()>0){
            cursor.close();
            return 1;
        } else {
            cursor.close();
            return 0;
        }


    }
    public long insertImageUser(ImageUser user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("image", user.getImg());
        contentValues.put("date",user.getDate().toString());
        contentValues.put("name",user.getName());
        return sqLiteDatabase.insert("image",null,contentValues);
    }

    public  User getUser(String username){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String Arg[] ={username};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  user " + "WHERE username = ?",Arg);

       // return null;
        if (cursor.moveToFirst()){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
          //  System.out.println(cursor.getString(3));
            user.setName(cursor.getString(3));
            user.setImg(cursor.getString(4));
          //  System.out.println(user.getImg() + "hdfhhdhfdhhfd");
           return  user;
        }
        return null;
    }
    public int updateUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("password",user.getPassword());
        contentValues.put("avatar",user.getImg());
        contentValues.put("name",user.getName());
        String whereClause = "id = ?";
        String args[] ={user.getId()+""};
        return sqLiteDatabase.update("user",contentValues,whereClause,args);

      //  return 1;
    }
    public List<ImageUser> getAllImageUser(String username){
        List<ImageUser> imageUsers= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String Arg[] = {username};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM image WHERE username = ?",Arg);
        while(cursor.moveToNext() && cursor != null){
            ImageUser user = new ImageUser();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setImg(cursor.getString(2));
            user.setDate(cursor.getString(3));
            user.setName(cursor.getString(4));
            imageUsers.add(user);
        }
        return  imageUsers;
    }
    public int deleteImage(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String Arg[]= {Integer.toString(id)};
      //  Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM image WHERE id = ?",Arg);
        String whereClause = "id = ?";
        return sqLiteDatabase.delete("image",whereClause,Arg);

    }
    public List<ImageUser> filterImageByName(String name){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<ImageUser> list = new ArrayList<>();
        String Arg[] = {"%"+name+"%"};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM image WHERE name LIKE ?",Arg);
        while(cursor.moveToNext()&& cursor != null){
            ImageUser user = new ImageUser();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setImg(cursor.getString(2));
            user.setDate(cursor.getString(3));
            user.setName(cursor.getString(4));
            list.add(user);
        }
        return list;

    }
}
