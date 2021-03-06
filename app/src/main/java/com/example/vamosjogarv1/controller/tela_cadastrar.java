package com.example.vamosjogarv1.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

@SuppressLint("Registered")
public class tela_cadastrar extends AppCompatActivity {
    Pessoa pessoa = new Pessoa();
    connection con = new connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_main);

        Button btnCadastrar;
        final EditText editNome,editSenha,editEmail,editHabilidade;
        RadioButton editSexoF,editSexoM;
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar_user);
        editEmail= (EditText)  findViewById(R.id.idEmail);
        editSenha = (EditText) findViewById(R.id.idSenha);
        editNome = (EditText) findViewById(R.id.idNome);
        editHabilidade = (EditText) findViewById(R.id.idHabilidade);

        final String HOST = con.getCadastrar();


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();
                    String senha = editSenha.getText().toString();
                    String habilidade = editHabilidade.getText().toString();
                    String sexo = pessoa.getSexo();

                    String URL = HOST;

                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(tela_cadastrar.this, "Todos os campos(OBRIGATORIOS) devem ser preenchidos" , Toast.LENGTH_LONG).show();
                    } else {

                        Ion.with(tela_cadastrar.this)
                                .load(URL)
                                .setBodyParameter("nome_app",nome)
                                .setBodyParameter("email_app",email)
                                .setBodyParameter("senha_app",senha)
                                .setBodyParameter("habilidade",habilidade)
                                .setBodyParameter("sexo",sexo)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        try {
                                            String RETORNO = result.get("CADASTRO").getAsString();
                                            if(RETORNO.equals("ERRO")) {
                                                Toast.makeText(tela_cadastrar.this, "OPS! ESte email ja esta cadastrado", Toast.LENGTH_LONG).show();
                                            }else if(RETORNO.equals("SUCESSO")){
                                                Toast.makeText(tela_cadastrar.this, "Cadastrado com sucesso,aguarde voce esta logando", Toast.LENGTH_LONG).show();
                                                Thread.sleep(1000);
                                                Intent it = new Intent(tela_cadastrar.this, tela_inicial_a.class);
                                                startActivity(it);
                                            }else {
                                                Toast.makeText(tela_cadastrar.this, "Ops! Ocorreu o erro," , Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {

                                            Toast.makeText(tela_cadastrar.this, "OPS OCORREU UM ERRO" + erro, Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });
                    }
                }
            });

    }


    public void onRadioButtonClicked(View view) {

        RadioGroup rb = (RadioGroup) findViewById(R.id.radio_group);

        switch (rb.getCheckedRadioButtonId()) {
            case R.id.radio_feminino:
                pessoa.setSexo("Feminino");
                break;
            case R.id.radio_masculino:
                pessoa.setSexo("Masculino");
                break;
        }


    }



    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

