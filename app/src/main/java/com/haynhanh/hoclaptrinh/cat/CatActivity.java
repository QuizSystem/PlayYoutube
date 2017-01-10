package com.haynhanh.hoclaptrinh.cat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.haynhanh.hoclaptrinh.AboutActivity;
import com.haynhanh.hoclaptrinh.R;
import com.haynhanh.hoclaptrinh.utils.Funs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CatActivity extends AppCompatActivity {

    // Load more
    private ProgressBar loadmore;

    // const
    public static String catId = "catId";
    public static String catTitle = "catTitle";

    // Declare Variables
    ListView listview;
    CatAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_activity);

        Firebase.setAndroidContext(this);

        arraylist = new ArrayList<HashMap<String, String>>();
        listview = (ListView) findViewById(R.id.listviewCat);

        loadmore = new ProgressBar(this);
        listview.addFooterView(loadmore);

        adapter = new CatAdapter(CatActivity.this, arraylist);
        listview.setAdapter(adapter);

        if (Funs.isNetworkAvailable(this)) {


//            // Execute DownloadJSON AsyncTask
//            try {
//                // new DownloadJSON().execute();
//                new AsynLoad().execute();
//            } catch (Exception e) {
//            }
            Firebase myFirebaseRef = new Firebase("https://hoclaptrinh.firebaseio.com/data/");
            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HashMap<String, String> td = (HashMap<String,String>) dataSnapshot.getValue();

                    Iterator myVeryOwnIterator = td.keySet().iterator();
                    while(myVeryOwnIterator.hasNext()) {
                        String key=(String)myVeryOwnIterator.next();
                        String value=(String)td.get(key);

                        //Log.e("mao", "mao " + "Key: " + key + " Value: " + value);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(CatActivity.catId, value);
                        map.put(CatActivity.catTitle, key);
                        arraylist.add(map);
                    }
                    adapter.notifyDataSetChanged();
                    listview.removeFooterView(loadmore);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });

        } else {
            Toast.makeText(this, "Please check Wifi/3G", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    private class AsynLoad extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {

            mProgressDialog = new ProgressDialog(CatActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);

            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
//            JSONParser jParser = new JSONParser();
//
//            List<NameValuePair> p = new ArrayList<NameValuePair>();
//            JSONObject json = jParser.makeHttpRequest(url, "GET", p);
//
//            try {
//
//                JSONArray items = json.getJSONArray("items");
//                for (int i = 0; i < items.length(); i++) {
//                    JSONObject c = items.getJSONObject(i);
//                    String id = c.getString("catId");
//                    String title = c.getString("catTitle");
//
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    map.put(CatActivity.catId, id);
//                    map.put(CatActivity.catTitle, title);
//                    arraylist.add(map);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return false;
//            }
            Firebase myFirebaseRef = new Firebase("https://hoclaptrinh.firebaseio.com/");
            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HashMap<String, String> td = (HashMap<String,String>) dataSnapshot.getValue();

                    Iterator myVeryOwnIterator = td.keySet().iterator();
                    while(myVeryOwnIterator.hasNext()) {
                        String key=(String)myVeryOwnIterator.next();
                        String value=(String)td.get(key);

                        //Log.e("mao", "mao " + "Key: " + key + " Value: " + value);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(CatActivity.catId, value);
                        map.put(CatActivity.catTitle, key);
                        arraylist.add(map);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mProgressDialog.dismiss();
            if (result) {
                adapter.notifyDataSetChanged();
                listview.removeFooterView(loadmore);
            }
            super.onPostExecute(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setHomeButtonEnabled(true);
        getMenuInflater().inflate(R.menu.menu_cat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // int itemId = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
//            quitApp();
            Toast.makeText(CatActivity.this, "Click Home", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
//        if (item.getItemId() == R.id.action_language) {
//            selectLanguage();
//        }
//
//        if (item.getItemId() == R.id.action_vote) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(Vari.linkStore));
//            startActivity(intent);
//        }
//        if (item.getItemId() == R.id.action_mail) {
//            try {
//                Intent gmail = new Intent(Intent.ACTION_VIEW);
//                gmail.setClassName("com.google.android.gm",
//                        "com.google.android.gm.ComposeActivityGmail");
//                gmail.putExtra(Intent.EXTRA_EMAIL,
//                        new String[] { Vari.mailSupport });
//                gmail.setData(Uri.parse(Vari.mailSupport));
//                gmail.putExtra(Intent.EXTRA_SUBJECT,
//                        getResources().getString(R.string.Feedback) + " app "
//                                + getResources().getString(R.string.app_name));
//                gmail.setType("plain/text");
//                gmail.putExtra(Intent.EXTRA_TEXT, "...");
//                startActivity(gmail);
//            } catch (Exception e) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri
//                        .parse("https://play.google.com/store/apps/details?id=com.google.android.gm"));
//                startActivity(intent);
//            }
//        }
//        if (item.getItemId() == R.id.action_search) {
//            Intent intent = new Intent(this, SearchActivity.class);
//            startActivity(intent);
//
//        }
        return super.onOptionsItemSelected(item);
    }

}