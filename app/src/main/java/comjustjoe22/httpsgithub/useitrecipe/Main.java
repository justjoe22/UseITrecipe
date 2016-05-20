package comjustjoe22.httpsgithub.useitrecipe;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Main extends ListActivity  {

    private static final String frBASE_URL = "https://useitrecipe.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Place Firebase code here
        Firebase.setAndroidContext(this);

        Firebase mFrBaseRef = new Firebase(frBASE_URL).child("message");

        final ArrayList fbItems = new ArrayList();

        final ListAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, fbItems);

        mFrBaseRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        fbItems.add(snapshot.getValue());

                        ListView listView = (ListView) findViewById(R.id.fbList);
                        listView.setAdapter(itemsAdapter);

                        Log.d("DATA", snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        //String message = "Server error. Refresh page";
                    }

                });

        //mFrBaseRef.child("Note3").setValue("Do you have data? You'll love Firebase.");

    }

}
