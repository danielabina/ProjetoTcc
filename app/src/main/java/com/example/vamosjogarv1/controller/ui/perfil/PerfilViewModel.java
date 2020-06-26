package com.example.vamosjogarv1.controller.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PerfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("olá");
    }

    public LiveData<String> getText() {
        return mText;
    }
}