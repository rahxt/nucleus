package com.crossedproductions.nucleus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Window;


public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    private Button mNextButton;
    private Button mSkipButton;

    private int mCurrentPage;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  if (Build.VERSION.SDK_INT>=21) {
        //     window = this.getWindow();
        //     window.setStatusBarColor(this.getResources().getColor(R.color.colorWhite));
        //  }

//welcome screen shared preference check

      SharedPreferences welcome = getSharedPreferences("welcome", MODE_PRIVATE);
        boolean firstStart = welcome.getBoolean("firstStart", true);

        if (!firstStart) {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
            Log.w("MainActivity","Redirecting to ListActivity / Since not FirstStart");

            finish();
        }

//skide
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextButton = (Button) findViewById(R.id.nextButton);
        mSkipButton = (Button) findViewById(R.id.skipButton);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

//onclick buttons
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                Log.w("MainActivity","Next button pressed");

            }
        });
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
                Log.w("MainActivity","Back button pressed");

            }
        });
    }


//the dots
    public void addDotsIndicator(int position) {

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        mDots = new TextView[3];
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorGray));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

//first launch save for welcome screen
        SharedPreferences welcome = getSharedPreferences("welcome", MODE_PRIVATE);
        SharedPreferences.Editor editor = welcome.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

// button change on page scroll
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if(i == 0){
                mNextButton.setEnabled(true);
                mSkipButton.setEnabled(false);
                mSkipButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mSkipButton.setText("");

            } else if (i == mDots.length - 1){

                mNextButton.setEnabled(true);
                mSkipButton.setEnabled(true);
                mSkipButton.setVisibility(View.VISIBLE);
                Log.w("MainActivity","Back button visible");

                mNextButton.setText("Start");
                Log.w("MainActivity","Next button changed to Start");

                mSkipButton.setText("Back");

           //next activity start and end welcome activity
               mNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.w("MainActivity","Start button pressed");

                        startActivity(new Intent(MainActivity.this, ListActivity.class));
                        Log.w("MainActivity","Redirecting to ListActivity");

                        mNextButton.setSaveEnabled(false);

                        finish();
                        Log.w("MainActivity","MainActivity Finihed");

                    }

                });

            } else{

                mNextButton.setEnabled(true);
                mSkipButton.setEnabled(true);
                mSkipButton.setVisibility(View.VISIBLE);
                mNextButton.setText("Next");
                mSkipButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };
    }

