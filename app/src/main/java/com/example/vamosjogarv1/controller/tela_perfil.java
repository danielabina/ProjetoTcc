package com.example.vamosjogarv1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import java.util.UUID;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.Pessoa;
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class tela_perfil extends AppCompatActivity {

    Button btnAvaliacoes;
    String pessoaa;
    Pessoa pessoa = new Pessoa();
    int idPessoa;
    String  nomePessoa,habilidade,senha,email,foto;
    EditText nome_,senha_,habilidade_;
    EditText editNome;
    EditText editSenha;
    EditText editHabilidade;
    TextView email_,editEmail;
    ImageView photo;
    EditarDadosAsyncTask editarDadosAsyncTask;
    UploadMultipart uploadMultipart;
    connection con = new connection();
    List<Pessoa> pessoaList;
    boolean control;
    //Declaring views
    private Button buttonChoose;
    Context mContext;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");
        nomePessoa = extras.getString("NOMEPESSOA");
        habilidade = extras.getString("HABILIDADE");
        senha = extras.getString("SENHA");
        email = extras.getString("EMAIL");
        foto = extras.getString("FOTO");

        nome_ = (EditText) findViewById(R.id.idNome);
        email_ = (TextView) findViewById(R.id.idEmail);
        senha_ = (EditText) findViewById(R.id.idSenha);
        habilidade_ = (EditText) findViewById(R.id.idHabilidade);
        photo = (ImageView) findViewById(R.id.idlogoP);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        popularEdit(nomePessoa, email, senha, habilidade);
        CarregarImagem();
    }

    /*
     * This is the method responsible for image upload
     * We need the full image path and the name for the image in this method
     * */
    public class UploadMultipart extends AsyncTask<Void,Void,Void> {

        //getting the actual path of the image
        String path ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            path = getPath(filePath);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                new MultipartUploadRequest(tela_perfil.this, con.getUpdate())
                        .setMethod("POST")
                        .addFileToUpload(path, "image") //Adding file
                        .addParameter("name", String.valueOf(idPessoa)) //Adding text parameter to the request
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (Exception exc) {
                Toast.makeText(tela_perfil.this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(tela_perfil.this, "ok", Toast.LENGTH_SHORT).show();
        }
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                photo.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        String result;
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(filePathColumn[0]);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void alterarImagem(View v){
        showFileChooser();
    }

    public void popularEdit(String nomePessoa, String email,String senha,String habilidade){
        nome_.setText(nomePessoa);
        email_.setText(email);
        senha_.setText(senha);
        habilidade_ .setText(habilidade);

    }

    public void CarregarImagem() {

        Picasso
                .with(this)
                .load(foto)
                .placeholder(R.drawable.carregando_animacao)
                .into(photo);
    }

    public void limparEdit(String nomePessoa, String email,String senha,String habilidade){
        nome_.setText("");
        email_.setText("");
        senha_.setText("");
        habilidade_ .setText("");
    }




    public void editar(View view) throws InterruptedException {
        editEmail= (TextView)  findViewById(R.id.idEmail);
        editSenha = (EditText) findViewById(R.id.idSenha);
        editNome = (EditText) findViewById(R.id.idNome);
        editHabilidade = (EditText) findViewById(R.id.idHabilidade);

        senha = editSenha.getText().toString();
        nomePessoa = editNome.getText().toString();
        habilidade = editHabilidade.getText().toString();


        uploadMultipart = new UploadMultipart();
        uploadMultipart.execute();

        editarDadosAsyncTask = new EditarDadosAsyncTask();
        editarDadosAsyncTask.execute();

        if(control == true){
            Toast.makeText(tela_perfil.this, "Alterações realizadas com sucesso", Toast.LENGTH_LONG).show();
        }

    }

    public class EditarDadosAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getUrlEditarDados();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;

        public EditarDadosAsyncTask( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("senha", senha);
            builder.appendQueryParameter("habilidade", habilidade);
            builder.appendQueryParameter("nome", nomePessoa);
            builder.appendQueryParameter("idPessoa", String.valueOf(idPessoa));
        }

        @Override
        protected void onPreExecute() {

            Log.i("APIListar", "onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListar", "doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            } catch (MalformedURLException e) {

                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());

            } catch (Exception e) {

                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            // Gerar uma requisição HTTP - POST - Result será um ArrayJson

            // conn

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

            } catch (Exception e) {

                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());

            }

            // Adicionar o TOKEN e/ou outros parâmetros como por exemplo
            // um objeto a ser incluido, deletado ou alterado.
            // CRUD completo

            try {

                query = builder.build().getEncodedQuery();

                OutputStream stream = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(stream, "utf-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();


            } catch (Exception e) {

                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());


            }

            // receber o response - arrayJson
            // http - código do response | 200 | 404 | 503

            try {

                response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {


                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    StringBuilder result = new StringBuilder();

                    String linha = null;

                    while ((linha = reader.readLine()) != null) {

                        result.append(linha);
                    }

                    return result.toString();

                } else {

                    return "HTTP ERRO: " + response_code;
                }


            } catch (Exception e) {

                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Erro: " + e.getMessage();

            } finally {

                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {
                JSONArray jsonArray = new JSONArray(result);
                pessoaList = new ArrayList<>();
                if(result.equals("[]")){
                    limparEdit(nomePessoa,email,senha,habilidade);
                    control = true;
                    popularEdit(nomePessoa,email,senha,habilidade);
                }
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        pessoa = new Pessoa(jsonObject.getInt("idPessoa"),
                                jsonObject.getString("nome"),
                                jsonObject.getString("senha"),
                                jsonObject.getString("habilidade"),
                                jsonObject.getString("sexo"));
                        pessoaList.add(pessoa);
                        Log.i("APIListar", "detalhe: -> " + pessoa.getIdPessoa() + " - " +pessoa.getNome());
                    }
                    Toast.makeText(tela_perfil.this, pessoaList.size() + " local Listados no LogCat", Toast.LENGTH_LONG)
                            .show();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

}
