package com.example.remedialexamen2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.remedialexamen2020.utilities.Utils;

public class UpdateActivity extends AppCompatActivity {
    String name;
    ConexionSQLiteHelper conn;
    EditText nameedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        this.name = getIntent().getStringExtra("EXTRA_SESSION_ID");
        nameedit = findViewById(R.id.nameInputUpdate);
        nameedit.setText(name);
        conn = new ConexionSQLiteHelper(getApplicationContext(), Utils.TABLA_PRODUCT, null, 1);
    }

    public void updateProduct(View view) {
        ContentValues cv = new ContentValues();
        cv.put(Utils.CAMPO_NAME,nameedit.getText().toString());
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        sqLiteDatabase.update(Utils.TABLA_PRODUCT, cv, Utils.CAMPO_NAME+"="+this.name, null);

    }
}
