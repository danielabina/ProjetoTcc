package com.example.vamosjogarv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.example.vamosjogarv1.controller.ui.avaliacao.AvaliacaoFragment;
import com.example.vamosjogarv1.controller.ui.faleConosco.FaleConoscoFragment;
import com.example.vamosjogarv1.controller.ui.perfil.PerfilFragment;
import com.example.vamosjogarv1.controller.ui.termodeuso.TermodeusoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import com.example.vamosjogarv1.R;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class tela_inicial_ extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //---------------------------------------------------------



        //------------------------------------------------------
        FloatingActionButton fab = findViewById(R.id.faleconosco);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent it = new Intent(tela_inicial_.this, tela_fale_conosco.class);
                startActivity(it);
            }
        });

        //---------------------------------------------------------
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_avalia, R.id.nav_perfil,R.id.nav_termo,R.id.nav_faleConosco)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //-----------------------------------------------------------------
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_inicial_, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.nav_perfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new PerfilFragment()).commit();
        }else if(id == R.id.nav_avalia){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new AvaliacaoFragment()).commit();
        }else if(id == R.id.nav_faleConosco){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new FaleConoscoFragment()).commit();
        }else if(id == R.id.nav_termo){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new TermodeusoFragment()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void BuscarEvento(View view) {
        Intent it = new Intent(tela_inicial_.this, tela_buscar_evento.class);
        startActivity(it);
    }

    public void CadastrarEvento(View view) {
        Intent it = new Intent(tela_inicial_.this, tela_cadastrar_evento.class);
        startActivity(it);
    }
}
