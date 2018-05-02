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

        for (int i = 0; i < 100; i++) {
            my_list.add("Item: " + String.valueOf(i));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                my_list );

        lv.setAdapter(arrayAdapter);
    }

}
