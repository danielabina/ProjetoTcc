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
import com.example.vamosjogarv1.controller.tela_avaliar;
import com.example.vamosjogarv1.controller.tela_detalhe_meus_proximos_eventos;

import java.util.ArrayList;
import java.util.List;

public class AdapterParticipantesAvaPersonalizado extends RecyclerView.Adapter<AdapterParticipantesAvaPersonalizado.MeuViewHolder> {
    Context ctx;
    List<Pessoa> listaPessoa = new ArrayList();
    String idPessoa,idEvento;

    public AdapterParticipantesAvaPersonalizado(List<Pessoa> pessoas, Context ctx1, String idPessoa,String idEvento) {
        this.ctx = ctx1;
        this.listaPessoa = pessoas;
        this.idPessoa = idPessoa;
        this.idEvento = idEvento;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,habilidade,sexo;
        @SuppressLint("WrongViewCast")
        public MeuViewHolder(@NonNull View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeParticipanteAva);
            sexo = (TextView) view.findViewById(R.id.sexoParticipanteAva);
            habilidade = (TextView) view.findViewById(R.id.habilidadeParticipanteAva);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Pessoa objSelecionado = listaPessoa.get(position);
            if(position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(ctx.getApplicationContext(), tela_avaliar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("IDPESSOALOGIN", idPessoa);
                intent.putExtra("IDPESSOAAVALIACAO", objSelecionado.getIdPessoa());
                intent.putExtra("IDEVENTO", idEvento);
                ctx.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public AdapterParticipantesAvaPersonalizado.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Pessoa pessoa = listaPessoa.get(i);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.activity_tela_lista_participantes_ava_personalizada, viewGroup, false);
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
