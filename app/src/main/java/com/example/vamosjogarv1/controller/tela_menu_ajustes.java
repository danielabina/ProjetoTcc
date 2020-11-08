package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

public class tela_menu_ajustes extends AppCompatActivity {
    int idPessoa;
    String  nomePessoa,habilidade,senha,email,foto;
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
        foto = extras.getString("FOTO");
    }

    public void MenuPerfil(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_perfil.class);
        it.putExtra("IDPESSOA", idPessoa);
        it.putExtra("NOMEPESSOA", nomePessoa);
        it.putExtra("SENHA", senha);
        it.putExtra("HABILIDADE", habilidade);
        it.putExtra("EMAIL", email);
        it.putExtra("FOTO", foto);
        startActivity(it);
    }
    public void MenuAvaliacoes(View view) {
        Intent it = new Intent(tela_menu_ajustes.this, tela_avaliacao.class);
        it.putExtra("IDPESSOA", idPessoa);
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

    public void Sair(View view){

        SharedPreferences.Editor pref = getSharedPreferences("info", MODE_PRIVATE).edit();

        pref.putString(encrypt("email"), "");

        pref.commit();

        SharedPreferences pref1 = getSharedPreferences("info", MODE_PRIVATE);
        String email = pref1.getString(encrypt("email"), null);

        if(email.isEmpty()) {
            finish();
            Intent abrePrincipal = new Intent(tela_menu_ajustes.this, tela_login.class);
            startActivity(abrePrincipal);
            finish();
        }
        finish();
    }

    public String encrypt(String palavra) {

        return Base64.encodeToString(palavra.getBytes(), Base64.DEFAULT);
    }

    public String decrypt(String palavra) {

        return new String(Base64.decode(palavra, Base64.DEFAULT));
    }
}
