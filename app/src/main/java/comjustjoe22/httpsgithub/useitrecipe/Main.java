package comjustjoe22.httpsgithub.useitrecipe;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Main extends ListActivity  {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://useitrecipe.firebaseio.com/";

    //private String mUsername;
    //private Firebase mFirebaseRef;
    //private ValueEventListener mConnectedListener;
    //private ListView userInfoList;
    //private ArrayList fbItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        Firebase mFirebaseRef = new Firebase(FIREBASE_URL).child("message");

        mFirebaseRef.setValue("Do you have data? You'll love Firebase.");

        final ArrayList fbItems = new ArrayList();

        mFirebaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                fbItems.add(snapshot.getValue());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //String message = "Server error. Refresh page";
            }
        });

        ArrayAdapter itemsAdapter =
                new ArrayAdapter (this, android.R.layout.simple_list_item_1, fbItems);

        ListView listView = (ListView) findViewById(R.id.firebaseView);
        listView.setAdapter(itemsAdapter);
    }


}
