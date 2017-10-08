package com.example.advaita.plege;

/**
 * Created by user on 11/3/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database1 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ngos.db";
    private static final String TABLE_NAME="ngos";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="nname";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PASSWORD="password";
    SQLiteDatabase db;

    private static final String TABLE_CREATE= "create table ngos(id integer primary key not null," +
            "nname text not null, username text not null,email text not null, password text not null); ";

    public Database1(Context context){

        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override

    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertNgo(Ngo u){

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String query=" select * from ngos";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,u.getName());
        values.put(COLUMN_USERNAME,u.getUsername());
        values.put(COLUMN_EMAIL,u.getEmail());
        values.put(COLUMN_PASSWORD,u.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String username)
    {
        db = this.getReadableDatabase();
        String query = "select username, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

}
