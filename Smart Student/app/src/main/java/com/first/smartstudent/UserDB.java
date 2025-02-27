package com.first.smartstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    private static final String DB_NAME="user_db";
    private static final String ID_COL="id";
    private static final String TABLE_NAME="credentials";
    private static final String NAME_COL="name";
    private static final String EMAIL_COL="email";
    private static final String MOBILE_COL="mobile";
    private static final String PASSWORD_COL="password";
    private static final String CONFIRM_PASSWORD_COL="confirm_password";
    private static final String IMAGE_COL="image";
    private static final int DB_VERSION=1;

    public UserDB(Context context) {
        super(context , DB_NAME , null , DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+ID_COL +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + EMAIL_COL + " TEXT, "
                + MOBILE_COL + " TEXT, "
                + PASSWORD_COL + " TEXT, "
                + CONFIRM_PASSWORD_COL + " TEXT, "
                + IMAGE_COL + " BLOB)";
        sqLiteDatabase.execSQL(query);
    }

    public void addUser(String name, String email, String mobile, String password, String confirmPassword, byte[] image){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME_COL,name);
        values.put(EMAIL_COL,email);
        values.put(MOBILE_COL,mobile);
        values.put(PASSWORD_COL,password);
        values.put(CONFIRM_PASSWORD_COL,confirmPassword);
        values.put(IMAGE_COL,image);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public UserDetails getUserByEmailPassword(String emailin, String passwordin){
        SQLiteDatabase db=this.getReadableDatabase();
        UserDetails user=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+EMAIL_COL+"=? AND "+PASSWORD_COL+"=?",new String[]{emailin,passwordin});

        if (cursor.moveToFirst()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String email=cursor.getString(2);
            String mobile=cursor.getString(3);
            String password=cursor.getString(4);
            String confirmPassword=cursor.getString(5);
            byte[] image=cursor.getBlob(6);
            user=new UserDetails(name,email,mobile,password,confirmPassword,image,id);
        }
        cursor.close();
        db.close();
        return user;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
