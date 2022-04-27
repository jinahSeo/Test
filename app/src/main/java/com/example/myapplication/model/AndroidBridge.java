package com.example.myapplication.model;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.myapplication.WViewActivity;

public class AndroidBridge {

    private String TAG = "AndroidBridge 로그";
    final public Handler handler = new Handler();

    //생성시 내부적으로 사용할 코드들
    private WebView mView;
    private WViewActivity mContext;

    public AndroidBridge(WebView mView, WViewActivity mContext)
    {
        //입력받은 값 저장
        this.mView = mView;
        this.mContext=mContext;
    }

    @JavascriptInterface
    public void call_log(final String msg){

        Log.d(TAG, msg);

        handler.post(new Runnable() {
            @Override
            public void run()
            {
                mView.loadUrl("javascipt:alert('["+msg+"]");
            }
        });
    }


}
