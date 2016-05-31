package comjustjoe22.httpsgithub.useitrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class details extends AppCompatActivity {

    private static final String frBASE_URL = "https://useitrecipe.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (null != intent) {

            String myKey = intent.getStringExtra("Key");

            //Place Firebase code here
            Firebase.setAndroidContext(this);

            final Firebase mFrBaseRef = new Firebase(frBASE_URL).child("recipes");

            mFrBaseRef.child(myKey).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            TextView textView = (TextView) findViewById(R.id.textView);
                            textView.setText(dataSnapshot.child("title").getValue().toString());

                            TextView textView2 = (TextView) findViewById(R.id.textView2);
                            textView2.setText(dataSnapshot.child("cookingTime").getValue().toString());

                            TextView textView3 = (TextView) findViewById(R.id.textView3);
                            textView3.setText(dataSnapshot.child("serves").getValue().toString());

                            TextView textView4 = (TextView) findViewById(R.id.textView4);
                            textView4.setText(dataSnapshot.child("instructions").getValue().toString());
                        }

                        @Override
                        public void onCancelled(FirebaseError databaseError) {

                        }
                    });

        }

    }
}
