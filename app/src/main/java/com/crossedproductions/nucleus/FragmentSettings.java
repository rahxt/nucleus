package com.crossedproductions.nucleus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.util.Log;


public class FragmentSettings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("FragmentSettings","Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
    }



}