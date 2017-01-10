package com.haynhanh.hoclaptrinh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by thieumao on 3/17/16.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String about = getResources().getString(R.string.about);
        setTitle(about);
    }
}
