package comjustjoe22.httpsgithub.useitrecipe;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends AppCompatActivity  {

    private SwipeRefreshLayout swipeContainer;
    private static final String frBASE_URL = "https://useitrecipe.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Place Firebase code here
        Firebase.setAndroidContext(this);

        final Firebase mFrBaseRef = new Firebase(frBASE_URL).child("message");

        final ArrayList fbItems = new ArrayList();

        final ListView listView = (ListView) findViewById(R.id.fbList);

        mFrBaseRef.addValueEventListener(
                new ValueEventListener() {
                    private int i = 0;

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                        int length = (int) snapshot.getChildrenCount();
                        String[] dataString = new String[length];
                        while(i < length) {
                            dataString[i] = iterator.next().getValue().toString();

                            fbItems.add(dataString[i]);

                            Log.d(Integer.toString(i), dataString[i]);
                            i++;
                        }

                        listView.invalidateViews();
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

        listView.setSmoothScrollbarEnabled(true);
        listView.setOverScrollMode(0);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.master_lin);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fbItems.clear();

                mFrBaseRef.addValueEventListener(
                        new ValueEventListener() {
                            private int i = 0;

                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                                int length = (int) snapshot.getChildrenCount();
                                String[] dataString = new String[length];
                                while(i < length) {
                                    dataString[i] = iterator.next().getValue().toString();

                                    fbItems.add(dataString[i]);

                                    Log.d(Integer.toString(i), dataString[i]);
                                    i++;
                                }

                                listView.invalidateViews();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                //String message = "Server error. Refresh page";
                            }

                        });

                swipeContainer.setRefreshing(false);

            }
        });


    }

}
//End of File
