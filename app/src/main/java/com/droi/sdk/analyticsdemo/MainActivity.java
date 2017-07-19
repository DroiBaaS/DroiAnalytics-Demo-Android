package com.droi.sdk.analyticsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.droi.sdk.analytics.DroiAnalytics;
import com.droi.sdk.core.AnalyticsCoreHelper;
import com.droi.sdk.core.Core;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    Context mContext;
    private Toast toast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.droi_example_activity_main);
        setClick();
        TextView deviceId = (TextView) findViewById(R.id.device_id);
        deviceId.setText(AnalyticsCoreHelper.getDeviceId());
        TextView appId = (TextView) findViewById(R.id.app_id);
        appId.setText(AnalyticsCoreHelper.getAppId());
        TextView channel = (TextView) findViewById(R.id.channel);
        channel.setText(Core.getChannelName(mContext));
    }

    @Override
    protected void onResume() {
        super.onResume();
        DroiAnalytics.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DroiAnalytics.onPause(this);
    }

    public void setClick() {
        Button activityButton = (Button) findViewById(R.id.activity_button);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity1.class));
            }
        });
        Button fragmentButton = (Button) findViewById(R.id.fragment_button);
        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        TabFragmentActivity.class));
            }
        });
        final EditText countEventKey = (EditText) findViewById(R.id.count_event_kv_key);
        final EditText countEventValue = (EditText) findViewById(R.id.count_event_kv_value);
        final LinearLayout countEventView = (LinearLayout) findViewById(R.id.count_event_kv);
        CheckBox countCheckBox = (CheckBox) findViewById(R.id.count_event_checkbox);
        countCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    countEventView.setVisibility(View.VISIBLE);
                } else {
                    countEventView.setVisibility(View.GONE);
                    countEventKey.setText("");
                    countEventValue.setText("");
                }
            }
        });

        Button countEventButton = (Button) findViewById(R.id.count_event_button);
        countEventButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Map<String, String> kv = new HashMap<String, String>();
                                                    if ((!countEventKey.getText().toString().trim().isEmpty()) && (!countEventValue.getText().toString().trim().isEmpty())) {
                                                        kv.put(countEventKey.getText().toString().trim(), countEventValue.getText().toString().trim());
                                                        String eventId = "count_event_with_kv";
                                                        DroiAnalytics.onEvent(mContext, eventId, kv);
                                                    } else {
                                                        String eventId = "count_event";
                                                        DroiAnalytics.onEvent(mContext, eventId);
                                                    }
                                                }
                                            }

        );
        final EditText valueEventKey = (EditText) findViewById(R.id.value_event_kv_key);
        final EditText valueEventValue = (EditText) findViewById(R.id.value_event_kv_value);
        final LinearLayout valueEventView = (LinearLayout) findViewById(R.id.value_event_kv);
        CheckBox valueCheckBox = (CheckBox) findViewById(R.id.value_event_checkbox);
        valueCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                         if (isChecked) {
                                                             valueEventView.setVisibility(View.VISIBLE);
                                                         } else {
                                                             valueEventView.setVisibility(View.GONE);
                                                             valueEventKey.setText("");
                                                             valueEventValue.setText("");
                                                         }
                                                     }
                                                 }
        );

        final EditText valueEventDu = (EditText) findViewById(R.id.value_event_du);
        Button valueEventButton = (Button) findViewById(R.id.value_event_button);
        valueEventButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Map<String, String> kv = new HashMap<String, String>();
                                                    try {
                                                        if (!valueEventDu.getText().toString().isEmpty()) {
                                                            int du = Integer.parseInt(valueEventDu.getText().toString().trim());
                                                            if ((!valueEventKey.getText().toString().trim().isEmpty()) && (!valueEventValue.getText().toString().trim().isEmpty())) {
                                                                kv.put(valueEventKey.getText().toString().trim(), valueEventValue.getText().toString().trim());
                                                                String eventId = "value_event_with_kv";
                                                                DroiAnalytics.onCalculateEvent(mContext, eventId, kv, du);
                                                            } else {
                                                                String eventId = "value_event";
                                                                DroiAnalytics.onCalculateEvent(mContext, eventId, null, du);
                                                            }
                                                        } else {
                                                            showToastInUiThread(getString(R.string.complete_value));
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        showToastInUiThread(getString(R.string.complete_right_value));
                                                    }
                                                }
                                            }
        );

        final EditText errorText = (EditText) findViewById(R.id.error_text);
        Button errorButton = (Button) findViewById(R.id.error_button);
        errorButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               if (!errorText.getText().toString().isEmpty()) {
                                                   String errorInfo = errorText.getText().toString().trim();
                                                   DroiAnalytics.onError(mContext, errorInfo);
                                               } else {
                                                   showToastInUiThread(getString(R.string.complete_error_info));
                                               }
                                           }
                                       }
        );
        Button crashButton = (Button) findViewById(R.id.crash_button);
        crashButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               throw new RuntimeException("\' fatal error happened \'");
                                           }
                                       }
        );
    }

    private void showToastInUiThread(final String msg) {
        Handler mainThread = new Handler(Looper.getMainLooper());
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(mContext.getApplicationContext(),
                            msg, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(msg);
                }
                toast.show();
            }
        });
    }
}

