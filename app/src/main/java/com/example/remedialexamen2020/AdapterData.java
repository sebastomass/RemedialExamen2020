package com.example.remedialexamen2020;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.remedialexamen2020.entities.Product;
import com.example.remedialexamen2020.utilities.Utils;

import java.util.ArrayList;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolderDatos> implements View.OnClickListener{
    private View.OnClickListener listener;

    ArrayList<Product> listDatos;
    private ClickListener clickListener;

    public AdapterData(ArrayList<Product> listDatos, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.listDatos = listDatos;
    }



    public interface ClickListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, null, false), listener;
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.name.setText(listDatos.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(this.listener != null){
            listener.onClick(v);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageButton editButton;
        ImageButton deleteButton;
        ConexionSQLiteHelper conn;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            conn = new ConexionSQLiteHelper(v.getContext(), Utils.TABLA_PRODUCT, null, 1);
            if(v.getId() == deleteButton.getId()){
                conn.delete(this.name.getText().toString());
                Toast toast = Toast.makeText(v.getContext(), this.name.getText()+" was deleted.", Toast.LENGTH_SHORT);
                toast.show();
            }
            if(v.getId() == editButton.getId()){
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", this.name.getText().toString());
                v.getContext().startActivity(intent);
            }

        }
    }
}
