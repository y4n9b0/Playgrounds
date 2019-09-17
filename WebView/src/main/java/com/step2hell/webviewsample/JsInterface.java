package com.step2hell.webviewsample;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

// JS调用Android的接口
public class JsInterface{

    private Context context;

    public JsInterface(Context context) {
        this.context = context;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void foo(String msg){
        Toast.makeText(context,"JS调用了Android的foo方法，参数:\n"+msg,Toast.LENGTH_SHORT).show();
    }
}
