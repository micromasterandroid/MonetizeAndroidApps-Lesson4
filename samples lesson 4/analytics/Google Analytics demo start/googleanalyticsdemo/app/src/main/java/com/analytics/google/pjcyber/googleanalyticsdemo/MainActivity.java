package com.analytics.google.pjcyber.googleanalyticsdemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracker = MyApplication.getTracker();

        tracker.setAppName("Google Analytics Android Demo");
        tracker.setScreenName("Main Screen");

        tracker.send(new HitBuilders.ScreenViewBuilder().build());


        Button clickButtom = (Button) findViewById(R.id.button);

        clickButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Clicks")
                        .setAction("Button")
                        .setLabel("Clicked demo")
                        .build());

                Toast.makeText(getApplicationContext(), "Tracked Click Event", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
