package com.example.vamosjogarv1.controller.ui.faleConosco;

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

public class FaleConoscoFragment extends Fragment {

    private FaleConoscoViewModel faleConoscoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        faleConoscoViewModel =
                ViewModelProviders.of(this).get(FaleConoscoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_faleconosco, container, false);
        return root;
    }
}
