package com.example.remedialexamen2020;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remedialexamen2020.utilities.Utils;

public class InsertProduct extends AppCompatActivity {
    EditText campoName;
    EditText campoQuantity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        this.campoName = findViewById(R.id.nameInputUpdate);
        this.campoQuantity = findViewById(R.id.quantityInput);
    }

    public void addNewProduct(View view) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, Utils.TABLA_PRODUCT, null, 1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.CAMPO_NAME, campoName.getText().toString());
        values.put(Utils.CAMPO_QUANTITY, campoQuantity.getText().toString());
        Long idResultante = db.insert(Utils.TABLA_PRODUCT, null, values);
        Toast.makeText(getApplicationContext(), "New Product Added with id: " + idResultante, Toast.LENGTH_SHORT).show();

    }
}
