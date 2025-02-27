package com.first.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isAirplaneModeEnabled(context.getApplicationContext())){
            Toast.makeText(context, "Airplane mode is on", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Airplane mode is off", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAirplaneModeEnabled(Context applicationContext) {
        return Settings.Global.getInt(applicationContext.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0)!=0;
    }
}
