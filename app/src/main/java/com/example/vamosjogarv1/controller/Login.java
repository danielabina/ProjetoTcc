package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vamosjogarv1.R;

public class Login extends AppCompatActivity {

    EditText editEmail,editSenha;
    Button btnEntrar,btnEsqueciSenha,btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        editEmail = (EditText) findViewById(R.id.idEmail);
        editSenha = (EditText) findViewById(R.id.idSenha);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEsqueciSenha = (Button) findViewById(R.id.btnRedefinir);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);


        /**
         * Chama tela cadastrar
         * @author : Daniela Bina
         * @date : 10/04/2020
         */
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Cadastrar.class);
                startActivity(it);
            }
        });

        /**
         * Chama tela de redefinição de senha
         * @author : Daniela Bina
         * @date : 10/04/2020
         */
        btnEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Redefinir.class);
                startActivity(it);
            }
        });

        /**
         * Chama tela inicial caso Login seja autorizado
         * @author : Daniela Bina
         * @date : 10/04/2020
         */
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
