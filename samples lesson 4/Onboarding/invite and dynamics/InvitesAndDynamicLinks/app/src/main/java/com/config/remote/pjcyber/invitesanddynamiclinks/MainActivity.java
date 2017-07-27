package com.config.remote.pjcyber.invitesanddynamiclinks;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appinvite.AppInviteInvitation;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseInvite";
    private static final int REQUEST_INVITE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInvite = (Button) findViewById(R.id.btn_invites);


        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new AppInviteInvitation.IntentBuilder("I'm Invite")
                        .setMessage("I'm invite you!")
                        .setDeepLink(Uri.parse("https://tzh3a.app.goo.gl/vqxq"))
                        .setCustomImage(Uri.parse("https://pbs.twimg.com/profile_images/606585229034135553/2NqZJYQI.png"))
                        .setCallToActionText("invitation is open")
                        .build();
                startActivityForResult(intent, REQUEST_INVITE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
                Log.d(TAG, "the invitation in cancel by the user");
            }
        }
    }


}
