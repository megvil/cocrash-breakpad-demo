package com.andye.cocrash.demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andye.cocrash.NativeBreakpad;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NativeBreakpad.init(Environment.getExternalStorageDirectory().getAbsolutePath());


        final String path = Environment.getExternalStorageDirectory().getAbsolutePath();


        Button btnTestNDKCrash = (Button) findViewById(R.id.btnTestNDKCrash);
        Button btnTestJavaCrash = (Button) findViewById(R.id.btnTestJavaCrash);
        btnTestNDKCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NativeBreakpad.testNativeCrash();
            }
        });

        btnTestJavaCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NativeBreakpad.testNativeCrash();
                Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
            }
        });


    }

}
