package com.example.vamosjogarv1.controller.ui.avaliacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vamosjogarv1.R;

public class AvaliacaoFragment extends Fragment {

    private AvaliacaoViewModel avaliacaoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        avaliacaoViewModel =
                ViewModelProviders.of(this).get(AvaliacaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_avaliacao, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        avaliacaoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
