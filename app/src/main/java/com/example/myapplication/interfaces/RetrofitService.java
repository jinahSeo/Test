package com.example.myapplication.interfaces;

import com.example.myapplication.model.PostResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    // @GET( EndPoint = 자원위치(URI) )

    @GET("memo")
    Call<PostResult> getPosts();
//    @GET("posts/{post}")
//    Call<PostResult> getPosts(@Path("post") String post); // post가 @Path("post")를 보고 @GET 내부 {post}에 대입


    //Ex) https://jsonplaceholder.typicode.com/posts/1 에서
    //    https://jsonplaceholder.typicode.com 은 URL
    //    /posts/1 은 URI
}
