package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.Database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase userDB = null;

    private Button btn_ok;
    private RecyclerView rcv;
    private List<User> userList;
    private UserAdapter uAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ok.findViewById(R.id.btn_ok);
        rcv = findViewById(R.id.recyclerView1);


        uAdapter = new UserAdapter(userList);
        rcv.setAdapter(uAdapter);

        //DB 생성
        userDB = AppDatabase.getInstance(this);

        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                try {
                    userList = userDB.getInstance(getApplicationContext()).userDao().getAll();
                    uAdapter = new UserAdapter(userList);
                    uAdapter.notifyDataSetChanged();

                    rcv.setAdapter(uAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    rcv.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {

                }
            }
        }

        InsertRunnable ir = new InsertRunnable();
        Thread t = new Thread(ir);
        t.start();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDB.destroyInstance();
        userDB = null;
    }
}
