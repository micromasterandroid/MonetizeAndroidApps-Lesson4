package com.analytics.pjcyber.admob_native_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-8078419072173314~3701163183");

        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adView);

        adView.loadAd(new AdRequest.Builder().build());
    }
}
