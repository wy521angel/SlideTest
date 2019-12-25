package com.example.wy521angel.slidetest;

import android.app.ListActivity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private String[] labels = new String[]{"layout方法，视图坐标方式", "layout方法，绝对坐标方式", "offset方法",
            "LayoutParams方法", "scrollBy方法", "scroller方法","TwoViewPager测试","两个ScrollView嵌套的滑动冲突"};

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
                startActivity(new Intent(this, Activity0.class).putExtra("label", 0));
                break;
            case 1:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 1));
                break;
            case 2:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 2));
                break;
            case 3:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 3));
                break;
            case 4:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 4));
                break;
            case 5:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 5));
                break;
            case 6:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 6));
                break;
            case 7:
                startActivity(new Intent(this, Activity0.class).putExtra("label", 7));
                break;
        }
    }
}
