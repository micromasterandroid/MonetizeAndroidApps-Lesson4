package com.analytics.google.pjcyber.googleanalyticsdemo;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;


public class MyApplication extends Application {

    private static GoogleAnalytics analytics;
    private static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();

        analytics = GoogleAnalytics.getInstance(this);

        tracker = analytics.newTracker(R.xml.tracking_id);

        // Provide unhandled exceptions reports. Do that first after creating the tracker
        tracker.enableExceptionReporting(true);

        // Enable Remarketing, Demographics & Interests reports
        // https://developers.google.com/analytics/devguides/collection/android/display-features
        tracker.enableAdvertisingIdCollection(true);

        // Enable automatic activity tracking for your app
        tracker.enableAutoActivityTracking(true);

    }

    public static GoogleAnalytics getAnalytics() {
        return analytics;
    }

    public static Tracker getTracker() {
        return tracker;
    }
}
