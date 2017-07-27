package com.analytics.google.pjcyber.googleanalyticsmanyevents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button btnLoadFragment;
    private Button btnLoadAlert;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLoadFragment = (Button) findViewById(R.id.btnLoadFragment);
        btnLoadAlert = (Button) findViewById(R.id.btnLoadAlert);


        /**
         * Tracking OneFragment View
         */
        btnLoadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OneFragment oneFragment = new OneFragment();
                fragmentTransaction.replace(R.id.frame_container, oneFragment);
                fragmentTransaction.commit();
            }
        });

        btnLoadAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Alert Tracked");
                MyApplication.getInstance().trackEvent("AlertDialog" , "Confirmation Type" , "Second screen");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Alert message")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                MyApplication.getInstance().trackEvent("AlertDialog" , "yes button" , "Confirmation");
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                MyApplication.getInstance().trackEvent("AlertDialog" , "cancel button" , "Confirmation");
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Enable this code to track the activity
        // if ga_autoActivityTracking set to false
        // MyApplication.getInstance().trackScreenView("Second Activity");
    }


}
