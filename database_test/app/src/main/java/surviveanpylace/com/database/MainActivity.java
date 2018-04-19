package surviveanpylace.com.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private DatabaseListHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_t1 = findViewById(R.id.button_tier1);
        Button button_t2 = findViewById(R.id.button_tier2);
        Button button_t3 = findViewById(R.id.button_tier3);
        lv = (ListView) (findViewById(R.id.testListView));

        handler = new DatabaseListHandler(this);
        handler.loadSqlDatabase(this);



//        db.readDatabase(this);
//
        button_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we want it to bring up everything from tier one
//                db.getTierOne();
//                System.out.println("Generated Tier One Stuff");
                ArrayList<String> results = handler.getTier(1);
                updateListView(results);
            }
        });
//
        button_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //Bring up everything from tier two
//                db.getTierTwo();
                ArrayList<String> results = handler.getTier(2);
                updateListView(results);
            }
        });

        button_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //we want this to bring up everything from Tier three
//                db.getTierThree();
                ArrayList<String> results = handler.getTier(3);
                updateListView(results);
            }
        });
    }

    private void updateListView(ArrayList<String> results) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                results
        );

        lv.setAdapter(arrayAdapter);
    }
}
