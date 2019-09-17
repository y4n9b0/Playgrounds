package com.step2hell.webviewsample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        webView.setWebChromeClient(new WebChromeClient() {
            // 重写onJsAlert，自己控制AlertDialog
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
//                b.setTitle("Alert");
//                b.setMessage(message);
//                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.confirm();
//                    }
//                });
//                b.setCancelable(false);
//                b.create().show();
//                return true;
//            }
        });

        // 加载入assets下的javascript代码
        webView.loadUrl("file:///android_asset/js.html");
        webView.addJavascriptInterface(new JsInterface(this), "test");

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                callJsFunction();
                break;
        }
    }

    private void callJsFunction() {
        String msg = "Hello JS from Android";
        if (Build.VERSION.SDK_INT < 19) {
            webView.loadUrl("javascript:callJS('" + msg + "')");
        } else {
            webView.evaluateJavascript("javascript:callJS('" + msg + "')", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.e("Bob", "onReceiveValue:" + value);
                }
            });
        }
    }
}
