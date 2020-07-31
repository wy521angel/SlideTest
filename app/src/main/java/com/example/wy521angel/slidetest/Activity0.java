package com.example.wy521angel.slidetest;

import android.app.Activity;
import android.os.Bundle;

public class Activity0 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int label = getIntent().getIntExtra("label", -1);
        switch (label) {
            case 0:
                setContentView(R.layout.activity_for_drag0);
                break;
            case 1:
                setContentView(R.layout.activity_for_drag1);
                break;
            case 2:
                setContentView(R.layout.activity_for_drag2);
                break;
            case 3:
                setContentView(R.layout.activity_for_drag3);
                break;
            case 4:
                setContentView(R.layout.activity_for_drag4);
                break;
            case 5:
                setContentView(R.layout.activity_for_drag5);
                break;
            case 6:
                setContentView(R.layout.activity_for_twoviewpager);
                break;
            case 7:
                setContentView(R.layout.activity_for_two_scrollview);
                break;
            case 8:
                setContentView(R.layout.nested_scalable_image_view);
                break;
            case 9:
                setContentView(R.layout.activity_for_two_scrollview2);
                break;
        }

    }
}
