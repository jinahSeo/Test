package com.example.myapplication.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputViewModel  extends ViewModel {

    //setValue(), postValue() 메소드 포함된 LiveData 클래스
    private MutableLiveData<String> currentInput;

    //currentInput 필드에 접근
    public MutableLiveData<String> getCurrentInput() {
        if(currentInput == null){
            currentInput = new MutableLiveData<String>();
        }

        return currentInput;
    }

}
