package com.droi.sdk.analyticsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by marek on 2016/10/19.
 */

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("DroiAnalyticsR", intent.getAction());
        Intent serviceIntent = new Intent(context.getApplicationContext(), TestService.class);
        context.startService(serviceIntent);
    }
}
