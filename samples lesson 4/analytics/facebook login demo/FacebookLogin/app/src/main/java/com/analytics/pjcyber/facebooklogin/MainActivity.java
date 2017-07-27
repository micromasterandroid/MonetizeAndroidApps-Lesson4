package com.analytics.pjcyber.facebooklogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    private AppEventsLogger logger;
    private Button trackButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginActivity();
        }

        logger = AppEventsLogger.newLogger(this);

        trackButton = (Button) findViewById(R.id.btnTrack);

        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Welcome Android", Toast.LENGTH_SHORT).show();
                logger.logEvent("Welcome Android");
            }
        });


    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginActivity();
    }

}
