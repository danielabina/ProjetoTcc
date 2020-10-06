package com.example.vamosjogarv1.model;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.tela_detalhe_evento;
import com.example.vamosjogarv1.model.AdapterEventosAnterioresPersonalizado;
import com.example.vamosjogarv1.model.Evento;

import java.util.List;



public class AdapterEventosProximosPersonalizado extends RecyclerView.Adapter<AdapterEventosProximosPersonalizado.MeuViewHolder>  {
    Context ctx;
    List<Evento> listaEvento;
    String idPessoa;


    public AdapterEventosProximosPersonalizado(List<Evento> eventos,Context ctx1, String idPessoa) {
        this.ctx = ctx1;
        this.idPessoa = idPessoa;
        this.listaEvento = eventos;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,dataHoraEv,endereco,modalidade;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeEventoProximos);
            dataHoraEv = (TextView) view.findViewById(R.id.dataHoraEvProximos);
            endereco = (TextView) view.findViewById(R.id.enderecoEvProximos);
            modalidade = (TextView) view.findViewById(R.id.modalidadeProximos);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Evento objSelecionado = listaEvento.get(position);
            if(position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(ctx.getApplicationContext(), tela_detalhe_evento.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("IDPESSOA", idPessoa);
                intent.putExtra("IDCANCHA",objSelecionado.getIdCancha());
                intent.putExtra("IDEVENTO",objSelecionado.getIdEvento());
                intent.putExtra("dataHoraEv", dataHoraEv.getText().toString());
                intent.putExtra("categoria", modalidade.getText().toString());
                intent.putExtra("endereco", endereco.getText().toString());
                intent.putExtra("nomeEvento", nome.getText().toString());
                ctx.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public AdapterEventosProximosPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Evento evento = listaEvento.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_evento_proximo_personalizada, viewGroup, false);
        AdapterEventosProximosPersonalizado.MeuViewHolder viewHolder = new AdapterEventosProximosPersonalizado.MeuViewHolder(linhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEventosProximosPersonalizado.MeuViewHolder meuViewHolder, int i) {
        Evento evento = listaEvento.get(i);
        TextView nome = meuViewHolder.nome;
        nome.setText(evento.getNomeEvento());
        TextView endereco = meuViewHolder.endereco;
        endereco.setText(evento.getEndereco());
        TextView dataHoraEv = meuViewHolder.dataHoraEv;
        dataHoraEv.setText(evento.getDataHoraa());
        TextView modalidade = meuViewHolder.modalidade;
        modalidade.setText(evento.getCategoria());
    }

    @Override
    public int getItemCount() {
        return listaEvento.size();
    }
}
