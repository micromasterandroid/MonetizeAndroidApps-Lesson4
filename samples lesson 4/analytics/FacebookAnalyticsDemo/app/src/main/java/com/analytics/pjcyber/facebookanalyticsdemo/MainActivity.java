package com.analytics.pjcyber.facebookanalyticsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;


public class MainActivity extends AppCompatActivity {

    private AppEventsLogger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logger = AppEventsLogger.newLogger(this);

        Button clickButtom = (Button) findViewById(R.id.btnClick);

        clickButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Click tracked", Toast.LENGTH_SHORT).show();

                Bundle parameters = new Bundle();
                parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, "USD");
                parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, "product");
                parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, "HDFU-8452");

                logger.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, 54.23, parameters);

            }
        });
    }
}
