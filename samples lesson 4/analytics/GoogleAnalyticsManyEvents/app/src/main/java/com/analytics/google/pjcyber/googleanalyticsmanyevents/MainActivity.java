package com.analytics.google.pjcyber.googleanalyticsmanyevents;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();


    private Button btnSecondScreen, btnSendEvent, btnException, btnAppCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSecondScreen = (Button) findViewById(R.id.btnSecondScreen);
        btnSendEvent = (Button) findViewById(R.id.btnSendEvent);
        btnException = (Button) findViewById(R.id.btnException);
        btnAppCrash = (Button) findViewById(R.id.btnAppCrash);



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
         * Event(Category, Action, Label)
         */
        btnSendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tracking Event
                MyApplication.getInstance().trackEvent("Cellphones", "buy", "Send event example");

                Toast.makeText(getApplicationContext(), "Event \'Book\' \'buy\' \'Event example\' is sent. Check it on Google Analytics Dashboard!", Toast.LENGTH_LONG).show();
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
                    MyApplication.getInstance().trackException(e);

                    Toast.makeText(getApplicationContext(), getString(R.string.toast_track_exception), Toast.LENGTH_LONG).show();

                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
        });

        /**
         * Tracking App Crashes
         * Manually generation app crash by dividing with zero
         */
        btnAppCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), getString(R.string.toast_app_crash), Toast.LENGTH_LONG).show();

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
