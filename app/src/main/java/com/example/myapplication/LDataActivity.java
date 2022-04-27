package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.model.InputViewModel;
import com.example.myapplication.model.ViewModelFactory;

public class LDataActivity extends AppCompatActivity {

    private TextView txtView;
    private Button btn_input;
    private EditText edt_txt;

    private InputViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldata);

        txtView = findViewById(R.id.textView);
        btn_input=findViewById(R.id.btn_input);
        edt_txt=findViewById(R.id.edt_txt);

        //뷰모델 객체 생성
        viewModel = new ViewModelProvider(this, new ViewModelFactory())
                .get(InputViewModel.class);
        //옵저버 정의
        Observer<String> inputObserver = newInput -> txtView.setText(newInput);

        //뷰모델에 옵저버 등록
        viewModel.getCurrentInput().observe(this, inputObserver);

        //UI 이벤트 처리 : 뷰모델 이용
        btn_input.setOnClickListener(view -> {
            String input = edt_txt.getText().toString();
            viewModel.getCurrentInput().setValue(input);
        });


    }
}