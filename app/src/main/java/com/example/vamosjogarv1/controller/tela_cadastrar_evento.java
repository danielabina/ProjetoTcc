package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vamosjogarv1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tela_cadastrar_evento extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView data;
    DatePickerDialog.OnDateSetListener  setListener;
    String categoria,dataHora,endereco;
    Button btnProximo;
    EditText nomeRua;


    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int hour = calendar.get(Calendar.HOUR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_evento);


        nomeRua = findViewById(R.id.nomeRua);

        data = findViewById(R.id.data);
        btnProximo = findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataHora.isEmpty() && endereco.isEmpty()) {
                    Toast.makeText(tela_cadastrar_evento.this, "O campo data-hora e endereco devem ser preenchidos" , Toast.LENGTH_LONG).show();
                } else {
                    Intent it = new Intent(tela_cadastrar_evento.this, tela_detalhe_local.class);
                    endereco = nomeRua.getText().toString();
                    dataHora = String.valueOf(calendar.get(Calendar.DATE)) + "/" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/" + String.valueOf(calendar.get(Calendar.YEAR)) + " "
                            + String.valueOf(calendar.get(Calendar.HOUR)) + ":" + String.valueOf(calendar.get(Calendar.MINUTE));
                    it.putExtra("categoria", categoria);
                    it.putExtra("dataHora", dataHora);
                    it.putExtra("endereco", endereco);
                    startActivity(it);
                    finish();
                }
            }
        });
        //---------------------------
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //---------------------------------------------------





                data.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               showDateTimeDialog(data);
                dataHora= String.valueOf(day) + String.valueOf(month) + String.valueOf(year) + String.valueOf(hour);
           }
           });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         categoria = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), categoria,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//-----------------------------------------------------
    private void showDateTimeDialog(final TextView data) {
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");
                        data.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(tela_cadastrar_evento.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
        }
    };

        new DatePickerDialog(tela_cadastrar_evento.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    public void onClick(View v) {

    }
}


