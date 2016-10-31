package com.droi.sdk.analyticsdemo;

import android.app.Application;
import android.util.Log;

import com.droi.sdk.analytics.DroiAnalytics;
import com.droi.sdk.analytics.Logger;
import com.droi.sdk.core.Core;
import com.droi.sdk.internal.DroiDeviceInfoCollector;

/**
 * Created by chenpei on 16/1/5.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "before initialize");
/*        //设置渠道号
        Core.setChannelName("CHANNEL_NEWSPAGE");
        //设置(appid,版本号versionName,包名)
        DroiDeviceInfoCollector.setInfo("c20umbzhy3-H51T_A0QYPM1AU69jMUKulQAA0C0O", "2.0.0", "com.freeme.widget.newspage");
        //上面两项设置必须要在初始化之前*/
        Core.initialize(this);
        DroiAnalytics.initialize(this);
        Log.e("MyApplication", "after initialize");
        // DroiAnalytics.setCrashReport(true);
        DroiAnalytics.setCrashReportWithLog(true);
        DroiAnalytics.setSandboxMode(true);
        // DroiAnalytics.enableActivityLifecycleCallbacks(this, true);
        // DroiAnalytics.setDefaultSendPolicy(SendPolicy.SCHEDULE);
        // DroiAnalytics.setScheduleConfig(false, 10);
        Logger.i("MyApplication", "onCreate");
    }
}
