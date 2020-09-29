package com.example.vamosjogarv1.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.connection;
import com.example.vamosjogarv1.controller.tela_detalhe_evento;

import java.util.List;

public class AdapterParticipantesEventoPersonalizado extends RecyclerView.Adapter<AdapterParticipantesEventoPersonalizado.MeuViewHolder> {
    Context ctx;
    List<Pessoa> listaPessoa;

    public AdapterParticipantesEventoPersonalizado(List<Pessoa> pessoas, Context ctx1) {
        this.ctx = ctx1;
        this.listaPessoa = pessoas;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,habilidade,sexo;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeParticipante);
            sexo = (TextView) view.findViewById(R.id.sexoParticipante);
            habilidade = (TextView) view.findViewById(R.id.habilidadeParticipante);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            }

    }

    @NonNull
    @Override
    public AdapterParticipantesEventoPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Pessoa pessoa = listaPessoa.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_participantes_eventos_personalizada, viewGroup, false);
        AdapterParticipantesEventoPersonalizado.MeuViewHolder viewHolder = new AdapterParticipantesEventoPersonalizado.MeuViewHolder(linhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipantesEventoPersonalizado.MeuViewHolder meuViewHolder, int i) {
        Pessoa pessoa = listaPessoa.get(i);
        TextView nome = meuViewHolder.nome;
        nome.setText(pessoa.getNome());
        TextView habilidade = meuViewHolder.habilidade;
        habilidade.setText(pessoa.getHabilidade());
        TextView sexo = meuViewHolder.sexo;
        sexo.setText(pessoa.getSexo());
    }

    @Override
    public int getItemCount() {
        return listaPessoa.size();
    }
}
