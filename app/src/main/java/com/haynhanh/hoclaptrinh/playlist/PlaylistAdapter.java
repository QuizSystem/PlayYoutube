package com.haynhanh.hoclaptrinh.playlist;

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
import com.haynhanh.hoclaptrinh.WatchActivity;
import com.haynhanh.hoclaptrinh.image.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaylistAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();
	
	public PlaylistAdapter(Context context,
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
		TextView playlisttitle;
//		TextView playlistvideoId;
		ImageView playlistthumbnails;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.playlist_item, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		playlisttitle = (TextView) itemView.findViewById(R.id.playlisttitle);
//		playlistvideoId = (TextView) itemView.findViewById(R.id.playlistvideoId);

		// Locate the ImageView in listview_item.xml
		playlistthumbnails = (ImageView) itemView.findViewById(R.id.playlistthumbnails);

		// Capture position and set results to the TextViews
		playlisttitle.setText(resultp.get(PlaylistActivity.title));
//		playlistvideoId.setText(resultp.get(Playlist.videoId));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(PlaylistActivity.thumbnails), playlistthumbnails);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Get the position
				resultp = data.get(position);
				String idVideo = resultp.get(PlaylistActivity.videoId);
				Intent intent = new Intent(context, WatchActivity.class);
				intent.putExtra("idVideo", idVideo);
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
