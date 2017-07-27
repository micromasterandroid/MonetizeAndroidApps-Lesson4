package facebookads.pjcyber.com.facebookbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.facebook.ads.*;

public class MainActivity extends AppCompatActivity {

    private NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nativeAd = new NativeAd(this, "116387145655690_116401418987596");
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Render the Native Ad Template
                View adView = NativeAdView.render(MainActivity.this, nativeAd, NativeAdView.Type.HEIGHT_300);
                LinearLayout nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
                // Add the Native Ad View to your ad container
                nativeAdContainer.addView(adView);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        // Initiate a request to load an ad.
        nativeAd.loadAd();

    }

}
