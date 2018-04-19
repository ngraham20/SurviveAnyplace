package com.example.loder.scrolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) (findViewById(R.id.dynListview));

        ArrayList<String> my_list = new ArrayList<>();
        my_list.add("foo");
        my_list.add("bar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                my_list );

        lv.setAdapter(arrayAdapter);
    }
}
