package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class tela_login extends AppCompatActivity {

    Pessoa pessoa = new Pessoa();
    Button btnEntrar,btnEsqueciSenha,btnCadastrar;
    EditText editEmail,editSenha;
    private String HOST = "http://192.168.0.114/Login/";

//    public void verificaDados() {
//
//        SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
//        String emailEncrypt = pref.getString(encrypt("email"), "");
//
//        String email = decrypt(emailEncrypt);
//
//        if(!email.isEmpty()) {
//
//            Intent abrePrincipal = new Intent(tela_login.this, tela_inicial_.class);
//            startActivity(abrePrincipal);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        //verificaDados();


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
                Intent it = new Intent(tela_login.this, tela_cadastrar.class);
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
                Intent it = new Intent(tela_login.this, tela_redefinir_senha.class);
                startActivity(it);
            }
        });

        /**
         * Chama tela inicial caso tela_login seja autorizado
         * @author : Daniela Bina
         * @date : 10/04/2020
         */
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(tela_login.this, tela_inicial_.class);
                startActivity(it);
//                String URL = HOST + "/logar.php";
//
//            String email = editEmail.getText().toString();
//            String senha = editSenha.getText().toString();
//                if (email.isEmpty() || senha.isEmpty()) {
//                    Toast.makeText(tela_login.this, "Todos os campos(OBRIGATORIOS) devem ser preenchidos" , Toast.LENGTH_LONG).show();
//                } else {
//
//                    Ion.with(tela_login.this)
//                            .load(URL)
//                            .setBodyParameter("email_app",email)
//                            .setBodyParameter("senha_app",senha)
//                            .asJsonObject()
//                            .setCallback(new FutureCallback<JsonObject>() {
//                                @Override
//                                public void onCompleted(Exception e, JsonObject result) {
//                                    try {
//                                        String RETORNO = result.get("LOGIN").getAsString();
//                                        if(RETORNO.equals("ERRO")) {
//                                            Toast.makeText(tela_login.this, "OPS! Email ou senham incorretos", Toast.LENGTH_LONG).show();
//                                        }else if(RETORNO.equals("SUCESSO")){
//                                            Toast.makeText(tela_login.this, "Carregando...", Toast.LENGTH_LONG).show();
//
//                                            String nome = result.get("NOME").getAsString();
//                                            String email = result.get("EMAIL").getAsString();
//
//                                            SharedPreferences.Editor pref = getSharedPreferences("info", MODE_PRIVATE).edit();
//
//                                            pref.putString(encrypt("nome"), encrypt(nome));
//                                            pref.putString(encrypt("email"), encrypt(email));
//
//                                            pref.commit();
//
//                                            Intent it = new Intent(tela_login.this, tela_inicial_.class);
//                                            startActivity(it);
//                                        }else {
//                                            Toast.makeText(tela_login.this, "Ops! Ocorreu o erro," , Toast.LENGTH_LONG).show();
//                                        }
//                                    } catch (Exception erro) {
//
//                                        Toast.makeText(tela_login.this, "OPS OCORREU UM ERRO" + erro, Toast.LENGTH_LONG).show();
//
//                                    }
//
////                                }
////                            });
//                }
//
         }
        });
    }

//    public String encrypt(String palavra) {
//
//        return Base64.encodeToString(palavra.getBytes(), Base64.DEFAULT);
//    }
//
//    public String decrypt(String palavra) {
//
//        return new String(Base64.decode(palavra, Base64.DEFAULT));
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }
}
