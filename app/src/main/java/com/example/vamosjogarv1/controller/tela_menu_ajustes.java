package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_menu_ajustes extends AppCompatActivity {
    int idPessoa;
    String  nomePessoa,habilidade,senha,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu_ajustes);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");
        nomePessoa = extras.getString("NOMEPESSOA");
        habilidade = extras.getString("HABILIDADE");
        email = extras.getString("EMAIL");
        senha = extras.getString("SENHA");
    }

    public void MenuPerfil(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_perfil.class);
        it.putExtra("IDPESSOA", idPessoa);
        it.putExtra("NOMEPESSOA", nomePessoa);
        it.putExtra("SENHA", senha);
        it.putExtra("HABILIDADE", habilidade);
        it.putExtra("EMAIL", email);
        startActivity(it);
        finish();
    }
    public void MenuAvaliacoes(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_avaliacao.class);
        startActivity(it);

    }

    public void FaleConoscoMenu(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_fale_conosco.class);
        startActivity(it);
    }

    public void TemordeUsoMenu(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_termo_de_uso_.class);
        startActivity(it);
    }
}
