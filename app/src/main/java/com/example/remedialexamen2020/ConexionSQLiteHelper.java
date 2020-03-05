package com.example.remedialexamen2020;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.remedialexamen2020.utilities.Utils;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utils.CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLA_PRODUCT);
        onCreate(db);
    }

    public void query(){

    }

    public void delete(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLA_PRODUCT+ " WHERE "+Utils.CAMPO_NAME+"='"+value+"'");
        db.close();
    }

    public void insert(){

    }

    public void update(){

    }
}
