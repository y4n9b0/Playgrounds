package com.step2hell.jni;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    static {
        Runtime.getRuntime().loadLibrary("sub");
//        System.loadLibrary("sub");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(String.valueOf(getPriceOfBook(new Book())));
    }

    public native String hello();
    public native float getPriceOfBook(Book book);
}
