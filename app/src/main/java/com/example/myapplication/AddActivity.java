package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;

public class AddActivity extends AppCompatActivity {

    private EditText edt_name1;
    private EditText edt_name2;
    private Button btn_ok;
    private Button btn_del;
    private AppDatabase userDB = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edt_name1=findViewById(R.id.edt_name);
        edt_name2=findViewById(R.id.edt_name2);
        btn_ok=findViewById(R.id.btn_add);
        btn_del=findViewById(R.id.btn_del);

        userDB = AppDatabase.getInstance(this);

        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                User user  = new User();

                user.firstName = edt_name1.getText().toString();
                user.lastName = edt_name2.getText().toString();
                userDB.getInstance(getApplicationContext()).userDao().insertAll(user);
            }
        }

        class DeleteRunnable implements Runnable {

            @Override
            public void run() {

                userDB.getInstance(getApplicationContext()).userDao().deleteAll();


            }
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertRunnable insertRunnable = new InsertRunnable();
                Thread addThread = new Thread(insertRunnable);
                addThread.start();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteRunnable deleteRunnable = new DeleteRunnable();
                Thread addThread = new Thread(deleteRunnable);
                addThread.start();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDB.destroyInstance();
    }
}