package comjustjoe22.httpsgithub.useitrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Main extends AppCompatActivity   {

    private static final String frBASE_URL = "https://useitrecipe.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Place Firebase code here
        Firebase.setAndroidContext(this);

        Firebase mFrBaseRef = new Firebase(frBASE_URL).child("message");

        final ArrayList fbItems = new ArrayList();

        final ListView listView = (ListView) findViewById(R.id.fbList);

        //final ListAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, fbItems);

        mFrBaseRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        fbItems.add(snapshot.getValue());

                        Log.d("DATA", snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        //String message = "Server error. Refresh page";
                    }

                });

        if(listView!=null)
        {
            listView.setAdapter(new ArrayAdapter(Main.this,android.R.layout.simple_list_item_1,fbItems));
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        //mFrBaseRef.child("Note3").setValue("Do you have data? You'll love Firebase.");

    }

}
