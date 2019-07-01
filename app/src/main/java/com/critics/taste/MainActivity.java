package com.critics.taste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.launcher_backgorund);

//        //HANDLE RECEIVING INTENTS
//        intent = getIntent();
//        intentAction = intent.getAction();
//        intentType = intent.getType();
//
//        if (Intent.ACTION_SEND.equals(intentAction) && intentType != null) {
//            if ("text/plain".equals(intentType)) {
//                handelSendText(intent);
//            }
//        }

    }
}
