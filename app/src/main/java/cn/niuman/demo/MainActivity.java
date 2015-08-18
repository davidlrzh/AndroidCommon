/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */

package cn.niuman.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.niuman.androidextendedviews.R;

public class MainActivity extends AppCompatActivity {

    private static final String[] samples = new String[]{
            "NFlowLinearLayout",
            "NXferImageView"
    };

    private static final Class<?>[] targets = {
            FlowLinearLayoutFragment.class,
            XferImageFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvFragments = (ListView)findViewById(R.id.lv_fragments);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, samples);
        lvFragments.setAdapter(adapter);

        lvFragments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, (Fragment) targets[position].newInstance()).commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
