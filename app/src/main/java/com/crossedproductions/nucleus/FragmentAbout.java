package com.crossedproductions.nucleus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class FragmentAbout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("FragmentAbout","Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);
    }



}