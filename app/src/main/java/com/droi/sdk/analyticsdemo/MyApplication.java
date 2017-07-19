package com.droi.sdk.analyticsdemo;

import android.app.Application;

import com.droi.sdk.analytics.DroiAnalytics;
import com.droi.sdk.core.Core;

/**
 * Created by chenpei on 16/1/5.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Core.initialize(this);
        DroiAnalytics.initialize(this);
        DroiAnalytics.setCrashReport(true);
        DroiAnalytics.enableActivityLifecycleCallbacks(this);
    }
}
