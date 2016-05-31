package comjustjoe22.httpsgithub.useitrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (null != intent) {
            String stringData = intent.getStringExtra("Item");
            TextView textView = (TextView) findViewById(R.id.textView);

            textView.setText(stringData);

        }

    }
}
