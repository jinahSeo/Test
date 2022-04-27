package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

/***
 * DTO 모델 - PostResult Class 선언
 * REST API로 받아올 데이터를 변환하여 매핑할 DTO 클래스 선언
 * REST API의 응답 데이터 구조에 맞게 모델 클래스(여기선 PostResult) 선언
 */

public class PostResult {

//    @SerializedName("userId")
//    private int userId;

    @SerializedName("id")
    private int id;

    private String title;

    @SerializedName("body")
    private String bodyValue;

    // toString()을 Override 하지 않으면 객체 주소값을 리턴
    @Override
    public String toString() {
        return "PostResult{" +
                "id=" + id +
                ", title=" + title +
                ", body=" + bodyValue + '\'' +
                '}';
    }
}
