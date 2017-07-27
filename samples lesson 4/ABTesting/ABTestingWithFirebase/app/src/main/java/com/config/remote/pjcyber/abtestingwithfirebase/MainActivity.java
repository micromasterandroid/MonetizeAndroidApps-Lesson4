package com.config.remote.pjcyber.abtestingwithfirebase;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final long CACHE_TIME_SECONDS = 0;
    private static final String EXPERIMENT_A = "variant_a";
    private static final String EXPERIMENT_B = "variant_b";

    private FirebaseRemoteConfig remoteConfig;
    private Button btnExperiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExperiment = (Button) findViewById(R.id.btn_experiment);

        remoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings config= new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        remoteConfig.setConfigSettings(config);

        remoteConfig.setDefaults(R.xml.remote_config);

        remoteConfig.fetch(CACHE_TIME_SECONDS).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Fetch Succeeded");
                    remoteConfig.activateFetched();
                } else {
                    Log.d(TAG, "Fetch Failed");
                }
                runExperiment();
            }
        });

        Log.d(TAG, "Last fetch status:" + remoteConfig.getInfo()
                .getLastFetchStatus() + ". Fetch Time millis:" + remoteConfig.getInfo().getFetchTimeMillis());


    }

    private void runExperiment() {
        String experiment = remoteConfig.getString("experiment_variant");
        FirebaseAnalytics.getInstance(this).setUserProperty("Experiment", experiment);

        if (experiment.equals(EXPERIMENT_A)) {
            btnExperiment.setText(EXPERIMENT_A);
            btnExperiment.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            btnExperiment.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else if (experiment.equals(EXPERIMENT_B)) {
            btnExperiment.setText(EXPERIMENT_B);
            btnExperiment.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        }
    }
}
