package com.crossedproductions.nucleus;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Splash Activity","Initiated");
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        Log.w("Splash Activity","Intent Finished");
        finish();
    }
}
