package com.example.android.carbonfootprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String [ ] args) {
        Date mdate = new Date();
        long time = mdate.getTime();

        System.out.println(time);
        System.out.println((int)time);

    }
}
