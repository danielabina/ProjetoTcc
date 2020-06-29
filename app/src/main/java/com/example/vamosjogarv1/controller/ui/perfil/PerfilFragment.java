package com.example.vamosjogarv1.controller.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.tela_avaliacao;
import com.example.vamosjogarv1.controller.tela_perfil;
import com.example.vamosjogarv1.controller.ui.avaliacao.AvaliacaoFragment;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        Button b = (Button) root.findViewById(R.id.btnAvaliacao);
        b.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {


    }
}
