package com.example.vamosjogarv1.controller.ui.termodeuso;

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

public class TermodeusoFragment extends Fragment {

    private TermodeusoViewModel termodeusoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        termodeusoViewModel =
                ViewModelProviders.of(this).get(TermodeusoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_termodeuso, container, false);

        return root;
    }
}
