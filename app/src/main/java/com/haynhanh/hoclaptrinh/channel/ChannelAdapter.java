package com.haynhanh.hoclaptrinh.channel;

/**
 * Created by thieumao on 3/16/16.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haynhanh.hoclaptrinh.R;
import com.haynhanh.hoclaptrinh.image.ImageLoader;
import com.haynhanh.hoclaptrinh.playlist.PlaylistActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ChannelAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ChannelAdapter(Context context,
                          ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
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
        // Declare Variables
//		TextView channelid;
        TextView channeltitle;
        TextView channelitemCount;
        ImageView channelthumbnails;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.channel_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
//		channelid = (TextView) itemView.findViewById(R.id.channelid);
        channeltitle = (TextView) itemView.findViewById(R.id.channeltitle);
        channelitemCount = (TextView) itemView.findViewById(R.id.channelitemCount);

        // Locate the ImageView in listview_item.xml
        channelthumbnails = (ImageView) itemView.findViewById(R.id.channelthumbnails);

        // Capture position and set results to the TextViews
//		channelid.setText(resultp.get(Channel.id));
        channeltitle.setText(resultp.get(ChannelActivity.title));
        channelitemCount.setText(resultp.get(ChannelActivity.itemCount) + " videos");
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(ChannelActivity.thumbnails), channelthumbnails);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get the position
                resultp = data.get(position);
//				Toast.makeText(context, ""+resultp.get(Channel.id),
//						Toast.LENGTH_SHORT).show();
                String idPlaylist = resultp.get(ChannelActivity.id);
                String titlePlaylist = resultp.get(ChannelActivity.title);
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("idPlaylist", idPlaylist);
                intent.putExtra("titlePlaylist", titlePlaylist);
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
