package surviveanpylace.com.surviveanyplace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectLanguage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);
        setTitle("Select Native Language");

        final Button buttonEn = (Button) findViewById(R.id.buttonEn);
        final Button buttonFr = (Button) findViewById(R.id.buttonFr);
        final Button buttonDu = (Button) findViewById(R.id.buttonDu);
        final Button buttonEs = (Button) findViewById(R.id.buttonEs);
        final Intent intent = new Intent(getBaseContext(), SelectTier.class);

        final SharedPreferences language = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);

        buttonEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = language.edit();
                editor.putString("language", "en");
                editor.apply();
                startActivity(intent);
            }
        });

        buttonFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = language.edit();
                editor.putString("language", "fr");
                editor.apply();
                startActivity(intent);
            }
        });

        buttonDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = language.edit();
                editor.putString("language", "du");
                editor.apply();
                startActivity(intent);
            }
        });

        buttonEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = language.edit();
                editor.putString("language", "es");
                editor.apply();
                startActivity(intent);
            }
        });

        //        buttonEn.setOnClickListener(new View.OnClickListener() {
 //          public void onClick(View view) {
//                // Code here executes on main thread after user presses button
//                test_view.setText("T1 Clicked!");
//            }
//        });
//
//        button_tier_two.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                // Code here executes on main thread after user presses button
//                test_view.setText("T2 Clicked!");
//            }
//        });
//
//        button_tier_three.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                // Code here executes on main thread after user presses button
//                test_view.setText("T3 Clicked!");
//            }
//        });

    }
}
