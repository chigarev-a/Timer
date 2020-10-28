package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private boolean isRunning = false;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        if(savedInstanceState != null){
            isRunning = savedInstanceState.getBoolean("isRunning");
            seconds = savedInstanceState.getInt("seconds");
            textView.setText(String.valueOf(seconds));
        }
        isRunTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("seconds", seconds);
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickPause(View view) {
        isRunning = false;
    }

    public void onClickStop(View view) {
        isRunning = false;
        seconds = 0;
        textView.setText("0");
    }

    private void isRunTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning){
                    seconds++;
                    textView.setText(String.valueOf(seconds));
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}