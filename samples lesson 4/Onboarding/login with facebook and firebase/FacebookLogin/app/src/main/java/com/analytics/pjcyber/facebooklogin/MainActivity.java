package com.analytics.pjcyber.facebooklogin;

import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private AppEventsLogger logger;
    private Button trackButton;
   // private TextView userInfo;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = AppEventsLogger.newLogger(this);

        trackButton = (Button) findViewById(R.id.btnTrack);
        //userInfo = (TextView) findViewById(R.id.usr_information);

        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Welcome Android", Toast.LENGTH_SHORT).show();
                logger.logEvent("Welcome Android");
            }
        });

        // To validate Session on Facebook
        /*if (AccessToken.getCurrentAccessToken() == null) {
            goLoginActivity();
        }*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null ) {
           // String info ="user: " + user.getDisplayName() + "\n" +"email: " + user.getEmail();
           // userInfo.setText(info);

        } else {
            goLoginActivity();
        }

    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        //LoginManager.getInstance().logOut();
        goLoginActivity();
    }

}
