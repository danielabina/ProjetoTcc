package com.example.vamosjogarv1.controller.ui.termodeuso;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TermodeusoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TermodeusoViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}