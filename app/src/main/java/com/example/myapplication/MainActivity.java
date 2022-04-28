package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.User;
import com.example.myapplication.Database.UserDao;
import com.example.myapplication.interfaces.RetrofitService;
import com.example.myapplication.model.PostResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.mindpipe.android.logging.log4j.LogConfigurator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    private Logger mLogger = Logger.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_db = findViewById(R.id.btn_db);
        Button btn_ld = findViewById(R.id.btn_ld);
        Button btn_wv = findViewById(R.id.btn_wv);


        LogConfigurator logCfg = new LogConfigurator();
        logCfg.setFileName(Environment.getExternalStorageDirectory()+"/logFile.log");

        Logger mLogger = Logger.getLogger(MainActivity.class);

        mLogger.info("onCreate");

        btn_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DBaseActivity.class);
                startActivity(i);
            }
        });

        btn_ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LDataActivity.class);
                startActivity(i);
            }
        });

        btn_wv.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), WViewActivity.class);
            startActivity(i);
        });


        //서버 통신

        // http://localhost:3001/memo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//
        RetrofitService service1 = retrofit.create(RetrofitService.class);

//        Call<PostResult> call = service1.getPosts("1");
        Call<PostResult> call = service1.getPosts();
//
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response)
            {
                if(response.isSuccessful()){
                    //통신 성공
                    PostResult result = response.body();
                    Log.d("RESULT", "onResponse: 성공, 결과\n" + result.toString());

                } else {
                    //통신 실패
                    Log.d("RESULT", "onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t)
            {
                //통신 실패(인터넷 끊김, 예외 발생 등 시스템적 이유)
                Log.d("RESULT", "onFailure: " + t.getMessage());

            }
        });

        // jackson example

        //Convert java object to json



    }
}
