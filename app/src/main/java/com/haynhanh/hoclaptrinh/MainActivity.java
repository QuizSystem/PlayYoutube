package com.haynhanh.hoclaptrinh;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.amobear.avengers", PackageManager.GET_SIGNATURES);
            for(Signature signature: packageInfo.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.e("KeyHash mao:", "mao " + Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        }catch (Exception e){
        }
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://hoclaptrinh.firebaseio.com/");
        //myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        myFirebaseRef.child("Lập Trình C++").setValue("UCMTrKf9vxnUcybL2QXXhb9A");
        myFirebaseRef.child("Lập Trình Java").setValue("UCMTrKf9vxnUcybL2QXXhb9A");
        myFirebaseRef.child("Lập Trình Android").setValue("UCMTrKf9vxnUcybL2QXXhb9A");
        myFirebaseRef.child("Lập Trình iOS").setValue("UCMTrKf9vxnUcybL2QXXhb9A");
        myFirebaseRef.child("Lập Trình Unity").setValue("UCMTrKf9vxnUcybL2QXXhb9A");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> td = (HashMap<String,String>) dataSnapshot.getValue();

                Iterator myVeryOwnIterator = td.keySet().iterator();
                while(myVeryOwnIterator.hasNext()) {
                    String key=(String)myVeryOwnIterator.next();
                    String value=(String)td.get(key);
                    Log.e("mao","mao " + "Key: " + key + " Value: " + value);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
