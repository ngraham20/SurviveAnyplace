package surviveanpylace.com.database;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_t1 = findViewById(R.id.button_tier1);
        Button button_t2 = findViewById(R.id.button_tier2);
        Button button_t3 = findViewById(R.id.button_tier3);

        final DatabaseHandler db = new DatabaseHandler(this);

        db.readDatabase(this);

        button_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we want it to bring up everything from tier one
                db.getTierOne();
                System.out.println("Generated Tier One Stuff");
            }
        });

        button_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bring up everything from tier two
            }
        });

        button_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we want this to bring up everything from Tier three
            }
        });
    }
}
