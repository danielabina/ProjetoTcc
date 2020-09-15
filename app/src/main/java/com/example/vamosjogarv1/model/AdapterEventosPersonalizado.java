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

public class AdapterEventosPersonalizado extends RecyclerView.Adapter<AdapterEventosPersonalizado.MeuViewHolder> {
    Context ctx;
    List<Evento> listaEvento;
    String idPessoa;

    connection con = new connection();

    public AdapterEventosPersonalizado(List<Evento> eventos,Context ctx1, String idPessoa) {
        this.ctx = ctx1;
        this.idPessoa = idPessoa;
        this.listaEvento = eventos;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeEvento);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Evento objSelecionado = listaEvento.get(position);
            if(position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(ctx.getApplicationContext(), tela_detalhe_evento.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putInt("ID",objSelecionado.getIdEvento());
                intent.putExtra("idLocal", objSelecionado.getIdCancha());
                intent.putExtra("IDPESSOA", idPessoa);
                ctx.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public AdapterEventosPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Evento evento = listaEvento.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_todos_eventos, viewGroup, false);
        AdapterEventosPersonalizado.MeuViewHolder viewHolder = new AdapterEventosPersonalizado.MeuViewHolder(linhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEventosPersonalizado.MeuViewHolder meuViewHolder, int i) {
        Evento evento = listaEvento.get(i);
        TextView nome = meuViewHolder.nome;
        nome.setText(evento.getNomeEvento());
    }

    @Override
    public int getItemCount() {
        return listaEvento.size();
    }
}
