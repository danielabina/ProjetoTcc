package com.example.vamosjogarv1.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.tela_avaliacao;
import com.example.vamosjogarv1.controller.tela_detalhe_meus_eventos_anteriores;

import java.util.List;
public class AdapterAvaliacaoPersonalizado extends RecyclerView.Adapter<AdapterAvaliacaoPersonalizado.MeuViewHolder>  {
    Context ctx;
    List<Avaliacao> listaAvaliacao;



    public AdapterAvaliacaoPersonalizado(List<Avaliacao> avaliacaoList,Context ctx1) {
        this.ctx = ctx1;
        this.listaAvaliacao = avaliacaoList;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView comentario;
                RatingBar valorEstrela;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            comentario = (TextView) view.findViewById(R.id.comentarioAva);
            valorEstrela = (RatingBar) view.findViewById(R.id.ratingBar2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            }

    }

    @NonNull
    @Override
    public AdapterAvaliacaoPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Avaliacao avaliacao = listaAvaliacao.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_avaliacao_personalizada, viewGroup, false);
        AdapterAvaliacaoPersonalizado.MeuViewHolder viewHolder = new AdapterAvaliacaoPersonalizado.MeuViewHolder(linhaView);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(@NonNull AdapterAvaliacaoPersonalizado.MeuViewHolder meuViewHolder, int i) {
        Avaliacao avaliacao = listaAvaliacao.get(i);
        TextView nome = meuViewHolder.comentario;
        nome.setText(avaliacao.getComentario());
        RatingBar valor = meuViewHolder.valorEstrela;
        valor.setRating(avaliacao.getValorEstrela());
    }

    @Override
    public int getItemCount() {
        return listaAvaliacao.size();
    }
}
