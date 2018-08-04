package com.lizhi.ls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            Logz.d(new NullPointerException("test print file"));
        }
        long end = System.currentTimeMillis();
        Log.e("time", String.valueOf(end - start));
    }
}
