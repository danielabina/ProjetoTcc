package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Login extends AppCompatActivity {

    Pessoa pessoa = new Pessoa();
    Button btnEntrar,btnEsqueciSenha,btnCadastrar;
    EditText editEmail,editSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        final String HOST = "http://192.168.0.114/Login/";


        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEsqueciSenha = (Button) findViewById(R.id.btnRedefinir);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        editEmail = (EditText)  findViewById(R.id.idEmail);
        editSenha = (EditText) findViewById(R.id.idSenha);


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

                String URL = HOST + "/logar.php";

            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Login.this, "Todos os campos(OBRIGATORIOS) devem ser preenchidos" , Toast.LENGTH_LONG).show();
                } else {

                    Ion.with(Login.this)
                            .load(URL)
                            .setBodyParameter("email_app",email)
                            .setBodyParameter("senha_app",senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        String RETORNO = result.get("LOGIN").getAsString();
                                        if(RETORNO.equals("ERRO")) {
                                            Toast.makeText(Login.this, "OPS! Email ou senham incorretos", Toast.LENGTH_LONG).show();
                                        }else if(RETORNO.equals("SUCESSO")){
                                            Toast.makeText(Login.this, "Carregando...", Toast.LENGTH_LONG).show();
                                            Intent it = new Intent(Login.this, telaInicial.class);
                                            startActivity(it);
                                        }else {
                                            Toast.makeText(Login.this, "Ops! Ocorreu o erro," , Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception erro) {

                                        Toast.makeText(Login.this, "OPS OCORREU UM ERRO" + erro, Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
