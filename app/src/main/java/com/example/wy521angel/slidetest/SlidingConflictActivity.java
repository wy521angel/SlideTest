package com.example.wy521angel.slidetest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SlidingConflictActivity extends ListActivity {

    private String[] labels = new String[]{"两个NestedScrollView嵌套的滑动冲突", "外部拦截法", "内部拦截法", "可缩放的图片与滑动的嵌套","两个ScrollView嵌套的滑动冲突"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                labels);
        getListView().setAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 7));
                break;
            case 1:
                startActivity(new Intent(this, DemoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, DemoActivity2.class));
                break;
            case 3:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 8));
                break;
            case 4:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 9));
                break;
        }
    }
}