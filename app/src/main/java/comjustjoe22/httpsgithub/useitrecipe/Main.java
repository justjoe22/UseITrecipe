package comjustjoe22.httpsgithub.useitrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        Firebase myDBRef = new Firebase("https://useitrecipe.firebaseio.com/");

        myDBRef.child("message").setValue("Do you have data? You'll love Firebase.");
    }
}
