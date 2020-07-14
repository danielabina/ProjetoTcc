package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.AdapterLocalPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Local;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_lista_local extends AppCompatActivity {

    connection connection = new connection();
    String urladdress = connection.getBusca();
    String[] name;
    String[] endereco;
    String[] imagepath;
    ListView listView;
    BufferedInputStream is;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_local);
        System.setProperty("http.keepAlive", "false");

        ListView listView = (ListView) findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        AdapterLocalPersonalizado customListView = new AdapterLocalPersonalizado(this, name, endereco, imagepath);
        listView.setAdapter(customListView);
//        ListView lista = (ListView) findViewById(R.id.lista);
//        List<Local> locais = todosOsLocais();
//        AdapterLocalPersonalizado adapter = new AdapterLocalPersonalizado(locais, locais, this);
//        lista.setAdapter(adapter);
//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//          @Override
//             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             Intent it = new Intent(tela_lista_local.this, tela_detalhe_local.class);
//             startActivity(it);
//              finish();
//               }
//              });
    }

    private void collectData() {
//Connection
        try {
            URL url=new URL(urladdress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();// get the response code, returns 200 if it's OK
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) { // response code is OK
                connection.getInputStream();
                }else{ // response code is not OK
                }


                String line;
                BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            connection.setRequestProperty("Connection","close");
                StringBuilder sb = new StringBuilder();
                while ((line = is.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();




//JSON
            try {
                JSONArray ja = new JSONArray(result);
                JSONObject jo = null;
                name = new String[ja.length()];
                endereco = new String[ja.length()];
                imagepath = new String[ja.length()];

                for (int i = 0; i <= ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    name[i] = jo.getString("name");
                    endereco[i] = jo.getString("endereco");
                    imagepath[i] = jo.getString("photo");
                }
            } catch (Exception ex) {

                ex.printStackTrace();
            }


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
