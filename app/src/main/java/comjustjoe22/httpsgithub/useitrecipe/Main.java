package comjustjoe22.httpsgithub.useitrecipe;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        final Firebase mFrBaseRef = new Firebase(frBASE_URL).child("recipes");

        final ArrayList fbItems = new ArrayList();

        final ListView listView = (ListView) findViewById(R.id.fbList);

        mFrBaseRef.addValueEventListener(
                new ValueEventListener() {
                    private int i = 0;

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                        int length = (int) snapshot.getChildrenCount();
                        while(i < length) {

                            String myKey = iterator.next().getKey();

                            String title = snapshot.child(myKey).child("title").getValue().toString();
                            String cookingTime = snapshot.child(myKey).child("cookingTime").getValue().toString();
                            String serves = snapshot.child(myKey).child("serves").getValue().toString();
                            String rank = snapshot.child(myKey).child("rank").getValue().toString();

                            fbItems.add(title + "\n" + cookingTime + " / " + serves + " / " + rank);

                            Log.d(Integer.toString(i), title);
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

            //OnClick for ListView
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String item = listView.getItemAtPosition(position).toString();
                            Toast.makeText(Main.this,"You selected : " + item, Toast.LENGTH_SHORT).show();

                            try {
                                Intent intent = new Intent(Main.this,Class.forName("comjustjoe22.httpsgithub.useitrecipe.details"));

                                intent.putExtra("Item",item);

                                startActivity(intent);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    });
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
                                while(i < length) {

                                    String myKey = iterator.next().getKey();

                                    String title = snapshot.child(myKey).child("title").getValue().toString();
                                    String cookingTime = snapshot.child(myKey).child("cookingTime").getValue().toString();
                                    String serves = snapshot.child(myKey).child("serves").getValue().toString();
                                    String rank = snapshot.child(myKey).child("rank").getValue().toString();

                                    fbItems.add(title + " / " + cookingTime + " / " + serves + " / " + rank);

                                    Log.d(Integer.toString(i), title);
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
