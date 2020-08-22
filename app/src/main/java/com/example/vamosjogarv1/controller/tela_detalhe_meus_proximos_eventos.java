package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class tela_detalhe_meus_proximos_eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_meus_proximos_eventos);

        findViewById(R.id.btnParticiparCancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarparticipar();
            }
        });

    }

    public void cancelarparticipar(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Cancelar participação..");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Tem Certeza que deseja cancelar sua participação?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_meus_proximos_eventos.this,"Voce confirmou o cancelamento",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_meus_proximos_eventos.this, tela_inicial_a.class);
                startActivity(it);
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_meus_proximos_eventos.this,"Voce continuara participando",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_meus_proximos_eventos.this, tela_detalhe_meus_proximos_eventos.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }
}
