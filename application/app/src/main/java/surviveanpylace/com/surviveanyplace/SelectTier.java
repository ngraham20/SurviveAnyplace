package surviveanpylace.com.surviveanyplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tier_select);
        setTitle(R.string.tier_select_title);

        final Button buttonTier1 = (Button) findViewById(R.id.buttonTier1);
        final Button buttonTier2 = (Button) findViewById (R.id.buttonTier2);
        final Button buttonTier3 = (Button) findViewById(R.id.buttonTier3);
        final Intent intent = new Intent(getBaseContext(), scrolly.class);

        buttonTier1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("EXTRA_TIER", 1);
                startActivity(intent);
            }
        });

        buttonTier2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("EXTRA_TIER", 2);
                startActivity(intent);
            }
        });

        buttonTier3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("EXTRA_TIER", 3);
                startActivity(intent);
            }
        });
    }
}
