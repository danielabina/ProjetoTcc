package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class tela_buscar_evento extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
Button btnbuscar;
    String categoria;
    String endereco;
    EditText nomeRua;
    int idPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_buscar_evento);

        btnbuscar= (Button) findViewById(R.id.btnBuscar);
        nomeRua = findViewById(R.id.nomeRuaT);
        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeRua != null){
                    endereco = nomeRua.getText().toString();
                }

                if (endereco.isEmpty()) {
                    Toast.makeText(tela_buscar_evento.this, "É necessário digitar rua ou bairro" , Toast.LENGTH_LONG).show();
                } else {
                    Intent it = new Intent(tela_buscar_evento.this, tela_lista_todos_eventos.class);
                    endereco = nomeRua.getText().toString();
                    it.putExtra("categoria", categoria);
                    it.putExtra("endereco", endereco);
                    it.putExtra("IDPESSOA",Integer.toString(idPessoa));


                    startActivity(it);

                    finish();
                }
            }
        });



        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoria = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), categoria,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
