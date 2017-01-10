package com.haynhanh.hoclaptrinh.channel;

/**
 * Created by thieumao on 3/16/16.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.haynhanh.hoclaptrinh.R;
import com.haynhanh.hoclaptrinh.utils.Funs;
import com.haynhanh.hoclaptrinh.utils.JSONParser;
import com.haynhanh.hoclaptrinh.utils.Vari;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelActivity extends AppCompatActivity {

    // Load more
    private boolean loadOne = false;
    private boolean loadFinish = false;
    private ProgressBar loadmore;

    // pageToken loadmore
    private String nextPageToken = "";
    private String url = "";
    // private HashMap<String, String> map;

    // const
    public static String id = "id";
    public static String title = "title";
    public static String thumbnails = "thumbnails";
    public static String itemCount = "itemCount";
    private String catId = "", catTitle = "";

    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ChannelAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_activity);

        arraylist = new ArrayList<HashMap<String, String>>();
        listview = (ListView) findViewById(R.id.listviewChannel);

        loadmore = new ProgressBar(this);
        listview.addFooterView(loadmore);

        adapter = new ChannelAdapter(ChannelActivity.this, arraylist);
        listview.setAdapter(adapter);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
                int lastitem = arg1 + arg2
                        + Integer.valueOf(Vari.maxResultsChannel);
                if (lastitem > arg3 && loadFinish == true) {
                    new AsynLoad().execute();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {

            }
        });
        if (Funs.isNetworkAvailable(this)) {
            // Execute DownloadJSON AsyncTask
            try {
                Bundle bundle = getIntent().getExtras();

                catId = bundle.getString("catId");
                catTitle = bundle.getString("catTitle");

                setTitle(catTitle);

                new AsynLoad().execute();
            } catch (Exception e) {
            }


        } else {
            Toast.makeText(this, "Please check Wifi/3G", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class AsynLoad extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            loadFinish = false;

            mProgressDialog = new ProgressDialog(ChannelActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            if (loadOne == false) {
                mProgressDialog.show();
                mProgressDialog.dismiss();
            }

            url = String.format(Vari.linkChannel, Vari.maxResultsChannel,
                    catId);
            if (nextPageToken.length() > 0) {
                url = url + "&pageToken=" + nextPageToken;
            }
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();

            List<NameValuePair> p = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url, "GET", p);

            try {
                nextPageToken = json.getString("nextPageToken");
            } catch (Exception e) {
            }

            try {
                JSONObject pageInfo = json.getJSONObject("pageInfo");
                int totalResults = pageInfo.getInt("totalResults");

                JSONArray items = json.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);
                    String id = c.getString("id");
                    JSONObject snippet = c.getJSONObject("snippet");
                    String title = snippet.getString("title");
                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                    JSONObject high = thumbnails.getJSONObject("default");
                    String urlthumbnails = high.getString("url");
                    JSONObject contentDetails = c
                            .getJSONObject("contentDetails");
                    String itemCount = contentDetails.getString("itemCount");
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ChannelActivity.id, id);
                    map.put(ChannelActivity.title, title);
                    map.put(ChannelActivity.itemCount, itemCount);
                    map.put(ChannelActivity.thumbnails, urlthumbnails);
                    arraylist.add(map);
                    if (arraylist.size() >= totalResults) {
                        return false;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mProgressDialog.dismiss();
            if (result) {
                loadOne = true;
                adapter.notifyDataSetChanged();
                loadFinish = true;
            } else {
                loadFinish = false;
                listview.removeFooterView(loadmore);
            }
            super.onPostExecute(result);
        }
    }





}