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

    // TODO: change this to your own FireBase URL
    private static final String frBASE_URL = "https://useitrecipe.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        //setContentView(R.layout.activity_main);

        Firebase mFrBaseRef = new Firebase(frBASE_URL).child("message");

        mFrBaseRef.setValue("Do you have data? You'll love Firebase.");

        final ArrayList fbItems = new ArrayList();

        mFrBaseRef.addValueEventListener(new ValueEventListener() {

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
