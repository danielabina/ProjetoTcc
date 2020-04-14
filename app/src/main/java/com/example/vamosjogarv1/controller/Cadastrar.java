package com.example.vamosjogarv1.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;

@SuppressLint("Registered")
public class Cadastrar extends AppCompatActivity {
    Pessoa pessoa = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_main);

        Button btnEntrar;
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        pessoa.setEmail((EditText)  findViewById(R.id.idEmail));
        pessoa.setSenha((EditText) findViewById(R.id.idSenha));


        if(true){
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(Cadastrar.this, Login.class);
                    startActivity(it);
                }
            });
        }else{

        }

    }
}

