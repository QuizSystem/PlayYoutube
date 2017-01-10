package com.haynhanh.hoclaptrinh.cat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haynhanh.hoclaptrinh.R;
import com.haynhanh.hoclaptrinh.channel.ChannelActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class CatAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public CatAdapter(Context context,
                      ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView catTitle;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.cat_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        catTitle = (TextView) itemView.findViewById(R.id.catTitle);


        // Capture position and set results to the TextViews
//		Catid.setText(resultp.get(Cat.id));
        catTitle.setText(resultp.get(CatActivity.catTitle));
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get the position
                resultp = data.get(position);
//				Toast.makeText(context, ""+resultp.get(Cat.id),
//						Toast.LENGTH_SHORT).show();
                String catId = resultp.get(CatActivity.catId);
                String catTitle = resultp.get(CatActivity.catTitle);

                Intent intent = new Intent(context, ChannelActivity.class);
                intent.putExtra("catId", catId);
                intent.putExtra("catTitle", catTitle);
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}