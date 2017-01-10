package com.haynhanh.hoclaptrinh;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.haynhanh.hoclaptrinh.utils.Funs;
import com.haynhanh.hoclaptrinh.utils.Vari;

public class WatchActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	// static private final String DEVELOPER_KEY
	// ="AIzaSyARYciEFMsq-bSS_Hb0GrPd_RjVjpYkIBU";
	static private String VIDEO = "SR6iYWJxHqs";

	// "fLexgOxsZu0";
	// "HoTfvqjFro0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.watch_activity);

		if (Funs.isNetworkAvailable(this)) {
			Bundle bundle = getIntent().getExtras();
			VIDEO = bundle.getString("idVideo");

			YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
			youTubeView.initialize(Vari.DEVELOPER_KEY, this);

		} else {
			Toast.makeText(this, "Please check Wifi/3G", Toast.LENGTH_SHORT)
					.show();
		}

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.xemphim_online, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getItemId() == R.id.action_back) {
//			finish();
//		}
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult error) {
		Toast.makeText(this, "Error :" + error.toString(), Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean arg2) {
		player.loadVideo(VIDEO);
		player.setFullscreen(true);
	}





}
