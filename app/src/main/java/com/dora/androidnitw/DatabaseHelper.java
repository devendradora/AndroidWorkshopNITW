package com.dora.androidnitw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Devendra Dora on 19-Mar-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="devdb";
    public static final int DB_VERSION=1;



    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTES(" +
                        "ID INTEGER PRIMARY KEY AUTO INCREMENT," +
                        "TITLE TEXT NOT NULL," +
                        "DESC TEXT NOT NULL)"
        );




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXIST NOTES");
    }

    public void addNotes(String title,String desc){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cval=new ContentValues();
        cval.put("DESC",desc);
        cval.put("TITLE",title);
        db.insert("NOTES", null, cval);

    }

    public Cursor getAllNotes(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT rowid _id, *  FROM NOTES",null);
        return cur;
    }

}
