package com.example.wy521angel.slidetest.hover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wy521angel.slidetest.R;

import java.util.ArrayList;
import java.util.List;

public class SuspendedLayoutActivity extends AppCompatActivity {

    private List<BlankFragment> fragments = new ArrayList();
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_layout);
        viewPager = findViewById(R.id.vViewpager);
        initFragments();
        initScrollableLayout();
    }

    private void initFragments() {
        fragments.add(BlankFragment.newInstance(0xfffdedbc));
        fragments.add(BlankFragment.newInstance(0xFFB1F1F5));
        fragments.add(BlankFragment.newInstance(0x30303333));
    }

    private void initScrollableLayout() {
        viewPager.setOffscreenPageLimit(2);
       viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
           @NonNull
           @Override
           public Fragment getItem(int position) {
               return fragments.get(position);
           }

           @Override
           public int getCount() {
               return fragments.size();
           }
       });
    }
}
