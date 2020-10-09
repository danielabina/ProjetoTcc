package com.example.vamosjogarv1.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;

import java.util.List;

public class AdapterParticipantesAvaPersonalizado extends RecyclerView.Adapter<AdapterParticipantesAvaPersonalizado.MeuViewHolder> {
    Context ctx;
    List<Pessoa> listaPessoa;

    public AdapterParticipantesAvaPersonalizado(List<Pessoa> pessoas, Context ctx1) {
        this.ctx = ctx1;
        this.listaPessoa = pessoas;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,habilidade,sexo;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeParticipanteAva);
            sexo = (TextView) view.findViewById(R.id.sexoParticipanteAva);
            //deve subir
            habilidade = (TextView) view.findViewById(R.id.habilidadeParticipanteAva);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            }

    }

    @NonNull
    @Override
    public AdapterParticipantesAvaPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Pessoa pessoa = listaPessoa.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_avaliacao, viewGroup, false);
        AdapterParticipantesAvaPersonalizado.MeuViewHolder viewHolder = new AdapterParticipantesAvaPersonalizado.MeuViewHolder(linhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipantesAvaPersonalizado.MeuViewHolder meuViewHolder, int i) {
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
