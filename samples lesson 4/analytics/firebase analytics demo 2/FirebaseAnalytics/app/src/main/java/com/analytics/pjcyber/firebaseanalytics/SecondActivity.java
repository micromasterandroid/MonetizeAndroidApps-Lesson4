package com.analytics.pjcyber.firebaseanalytics;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.analytics.pjcyber.firebaseanalytics.MyApplication.firebaseAnalyticsInstance;

public class SecondActivity extends AppCompatActivity {


    private Button btnAppCrash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAnalyticsInstance().setCurrentScreen(this, getClass().getSimpleName(), null);

        btnAppCrash = (Button) findViewById(R.id.btnAppCrash);

        /**
         * Tracking App Crashes
         * Manually generation app crash by dividing with zero
         */
        btnAppCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Crash Reported", Toast.LENGTH_LONG).show();

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        int answer = 12 / 0;
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 1500);
            }
        });
    }


}
