package com.example.remedialexamen2020;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remedialexamen2020.entities.Product;
import com.example.remedialexamen2020.utilities.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> listProduct;
    RecyclerView recyclerViewProduct;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ConexionSQLiteHelper(getApplicationContext(), Utils.TABLA_PRODUCT, null, 1);
        listProduct = new ArrayList<>();
        recyclerViewProduct =findViewById(R.id.RecyclerId);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        queryProducts();
        AdapterData adapter = new AdapterData(listProduct, new AdapterData.ClickListener() {

            @Override
            public void onEditClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                String name = ((TextView) recyclerViewProduct.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.productName)).getText().toString();
                conn.delete(name);
            }
        });
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Quantity: "+listProduct.get(recyclerViewProduct.getChildAdapterPosition(v)).getQuantity(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewProduct.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InsertProduct.class);
                startActivity(intent);
            }
        });
    }






    private void queryProducts() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Product product = null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLA_PRODUCT + " ORDER BY CAST(" + Utils.CAMPO_QUANTITY + " AS INTEGER);", null);

        while(cursor.moveToNext()){
            product = new Product();
            product.setName(cursor.getString(0));
            product.setQuantity(cursor.getString(1));
            listProduct.add(product);
        }
    }
}
