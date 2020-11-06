package com.example.vamosjogarv1.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class AdapterLocalPersonalizado extends RecyclerView.Adapter<AdapterLocalPersonalizado.ViewHolder> {
    private Context context;
    private List<Local> listaLocal;
    String idPessoa,dataHora,nomEvento,photo;
    public AdapterLocalPersonalizado(List<Local> locais,Context ctx1, String idPessoa,String dataHora,String nomEvento) {
        this.context = ctx1;
        this.idPessoa = idPessoa;
        this.dataHora = dataHora;
        this.nomEvento = nomEvento;
        this.listaLocal = locais;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = inflater
                .inflate(R.layout.activity_tela_lista_local_personalizada, parent, false);
        ViewHolder viewHolder = new ViewHolder(linhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocalPersonalizado.ViewHolder holder, int position) {
        Local local = listaLocal.get(position);
        TextView nome = holder.nome;
        nome.setText(local.getNome());
        TextView categoria = holder.categoria;
        categoria.setText(local.getCategoria());
        TextView valor = holder.valor;
        valor.setText(local.getValor());
        TextView endereco = holder.endereco;
        endereco.setText(local.getEndereco());
         photo =  local.getPhoto();

    }

    @Override
    public int getItemCount() {
        return listaLocal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, categoria, endereco, valor;
        ImageView foto;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.namec);
            categoria = (TextView) view.findViewById(R.id.categoria);
            valor = (TextView) view.findViewById(R.id.valor);
            endereco = (TextView) view.findViewById(R.id.ende);
            foto = (ImageView) view.findViewById(R.id.imgFotoLocal);

            Picasso
                    .with(view.getContext())
                    .load(photo)
                    .placeholder(R.drawable.carregando_animacao)
                    .into(foto);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Local objSelecionado = listaLocal.get(position);
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context.getApplicationContext(), tela_detalhe_local.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", objSelecionado.getId());
                intent.putExtra("idLocal", objSelecionado.getId());
                intent.putExtra("IDPESSOA", idPessoa);
                intent.putExtra("nomeEvento", nomEvento);
                intent.putExtra("dataHora", dataHora);
                context.startActivity(intent);
            }
        }
    }
}