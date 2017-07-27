package com.notifications.pjcyber.firebasenotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoData = (TextView) findViewById(R.id.textView2);

        if (getIntent().getExtras() != null) {
            for (String key: getIntent().getExtras().keySet()){
                String value = getIntent().getExtras().getString(key);
                infoData.append("\n" + key + ":" + value);
            }
        }

    }
}
