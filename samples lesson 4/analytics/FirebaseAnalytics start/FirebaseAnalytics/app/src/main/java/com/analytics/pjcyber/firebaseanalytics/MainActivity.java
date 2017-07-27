package com.analytics.pjcyber.firebaseanalytics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    private Button btnClick;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        btnClick = (Button) findViewById(R.id.btnClickTracker);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Click tracked" , Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Click Button");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Click Demo");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.TUTORIAL_COMPLETE, bundle);

            }
        });
    }
}
