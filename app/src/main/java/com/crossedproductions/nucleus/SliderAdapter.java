package com.crossedproductions.nucleus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context) {

        this.context = context;
    }

    public int[] slide_images = {

            R.drawable.eat,
            R.drawable.sleep,
            R.drawable.work
    };

    public String[] slide_headings = {

            "Title One",
            "Title Two",
            "Title Three"
    };

    public String[] slide_description = {

            "Description textarea",
            "Description textarea",
            "Description textarea"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slider_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slider_header);
        TextView slideDescription = (TextView) view.findViewById(R.id.slider_description);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
