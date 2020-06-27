package com.example.vamosjogarv1.model;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vamosjogarv1.R;

import com.example.vamosjogarv1.controller.tela_cadastrar_evento_proximo;

import java.util.List;

public class AdapterLocalPersonalizado extends BaseAdapter {
    private final List<Local> locais;
    private final Activity act;

    public AdapterLocalPersonalizado(List<Local> local, List<Local> locais, tela_cadastrar_evento_proximo act) {
        this.locais = locais;
        this.act = act;
    }


    @Override
    public int getCount() {
        return locais.size();
    }

    @Override
    public Object getItem(int position) {
        return locais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_lista_local_personalizada, parent, false);

        Local local = locais.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.lista_local_personalizada_nome);
        TextView descricao = (TextView)
                view.findViewById(R.id.lista_local_personalizada_descricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.lista_local_personalizada_imagem);

        nome.setText(local.getNome());
        descricao.setText(local.getDescricao());

        Categoria categoria = local.getCategoria();

        if (categoria.equals(Categoria.Futebol)) {
            imagem.setImageResource(R.drawable.oi);
        } else if (categoria.equals(Categoria.Voleibol)) {
            imagem.setImageResource(R.drawable.ola);
        } else if (categoria.equals(Categoria.Handebol)) {
            imagem.setImageResource(R.drawable.rrr);
        }

        return view;
    }
}
