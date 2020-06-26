package com.example.vamosjogarv1.controller.ui.avaliacao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AvaliacaoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AvaliacaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}