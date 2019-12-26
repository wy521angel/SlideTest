package com.example.wy521angel.slidetest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * Created by wy521angel on 2017/11/13.
 */

public class DemoActivity extends Activity {

    private HorizontalScrollViewEx mListContainer;
    private static final String TAG = "DemoActivity_1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);
        Log.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        final int screenWidth = Utils.getScreenMetrics(this).widthPixels;
        final int screenHeight = Utils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout,
                    mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }

    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item,
                R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DemoActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}


























