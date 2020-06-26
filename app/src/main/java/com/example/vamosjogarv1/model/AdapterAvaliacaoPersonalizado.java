package com.example.vamosjogarv1.model;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.tela_avaliacao;
import com.example.vamosjogarv1.controller.tela_lista_todos_eventos;

import java.util.List;

public class AdapterAvaliacaoPersonalizado extends BaseAdapter {
    private final List<Evento> eventos;
    private final Activity act;

    public AdapterAvaliacaoPersonalizado(List<Evento> evento, List<Evento> eventos, tela_avaliacao act) {
        this.eventos = eventos;
        this.act = act;
    }


    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_lista_local_personalizada, parent, false);

        Evento evento = eventos.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.lista_local_personalizada_nome);
        TextView descricao = (TextView)
                view.findViewById(R.id.lista_local_personalizada_descricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.lista_local_personalizada_imagem);

        nome.setText(evento.getNome());
        descricao.setText(evento.getDescricao());

        Categoria categoria = evento.getCategoria();

        if (categoria.equals(Categoria.Futebol)) {
            imagem.setImageResource(R.drawable.ic_menu_camera);
        } else if (categoria.equals(Categoria.Voleibol)) {
            imagem.setImageResource(R.drawable.ic_menu_slideshow);
        } else if (categoria.equals(Categoria.Handebol)) {
            imagem.setImageResource(R.drawable.ic_menu_slideshow);
        }

        return view;
    }
}
