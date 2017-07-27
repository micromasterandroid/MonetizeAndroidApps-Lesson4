package com.analytics.pjcyber.firebaseanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import static com.analytics.pjcyber.firebaseanalytics.MyApplication.firebaseAnalyticsInstance;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private Button btnClick;
    private Button btnSecondScreen, btnException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAnalyticsInstance().setCurrentScreen(this, getClass().getSimpleName(), null);

        btnSecondScreen = (Button) findViewById(R.id.btnSecondScreen);
        btnClick = (Button) findViewById(R.id.btnClickTracker);
        btnException = (Button) findViewById(R.id.btnException);


        /**
         * Launching another activity to track the other screen
         */
        btnSecondScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        /**
         * Event tracking
         */

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Click tracked" , Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Click Button");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Click Demo");
                firebaseAnalyticsInstance().logEvent(FirebaseAnalytics.Event.TUTORIAL_COMPLETE, bundle);
            }
        });


        /**
         * Tracking Exception Manually
         * All known exceptions can be tracking this way
         * using Try & Catch
         */
        btnException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = null;
                    if (name.equals("Homer Simpson")) {
                        /* Never comes here as it throws null pointer exception */
                    }
                } catch (Exception e) {
                    // Tracking exception
                    FirebaseCrash.report(e);
                    FirebaseCrash.logcat(Log.ERROR, TAG, "Exceptions reported");

                    Toast.makeText(getApplicationContext(), "Exceptions reported", Toast.LENGTH_LONG).show();

                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
        });
    }
}
