package com.example.vamosjogarv1.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;

import com.example.vamosjogarv1.controller.connection;
import com.example.vamosjogarv1.controller.tela_detalhe_local;
import com.example.vamosjogarv1.controller.tela_lista_local;

import java.io.InputStream;
import java.util.List;

public class AdapterLocalPersonalizado extends RecyclerView.Adapter<AdapterLocalPersonalizado.MeuViewHolder> {

    Context ctx;
    List<Local> listaLocal;

    connection con = new connection();

    public AdapterLocalPersonalizado(List<Local> locais,Context ctx1) {
        this.ctx = ctx1;
        this.listaLocal = locais;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,categoria,endereco,valor;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);

            nome = (TextView) view.findViewById(R.id.namec);
            categoria = (TextView) view.findViewById(R.id.categoria);
            valor = (TextView) view.findViewById(R.id.valor);
            endereco = (TextView) view.findViewById(R.id.ende);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Local objSelecionado = listaLocal.get(position);

            if(position != RecyclerView.NO_POSITION){


                Intent intent = new Intent(ctx.getApplicationContext(), tela_detalhe_local.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle = new Bundle();
                bundle.putInt("ID",objSelecionado.getId());
                intent.putExtra("idLocal", objSelecionado.getId());

                ctx.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Local local = listaLocal.get(i);

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View linhaView = inflater.inflate(R.layout.activity_tela_lista_local_personalizada, viewGroup, false);

        MeuViewHolder viewHolder = new MeuViewHolder(linhaView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocalPersonalizado.MeuViewHolder meuViewHolder, int i) {
        Local local = listaLocal.get(i);
            TextView nome = meuViewHolder.nome;
       nome.setText(local.getNome());
        TextView categoria = meuViewHolder.categoria;
        categoria.setText(local.getCategoria());
        TextView valor = meuViewHolder.valor;
        valor.setText(local.getValor());
        TextView endereco = meuViewHolder.endereco;
        endereco.setText(local.getEndereco());
        }



    @Override
    public int getItemCount() {
        return listaLocal.size();
    }

}
