package com.example.vamosjogarv1.controller.ui.faleConosco;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaleConoscoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FaleConoscoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("olá");
    }

    public LiveData<String> getText() {
        return mText;
    }
}