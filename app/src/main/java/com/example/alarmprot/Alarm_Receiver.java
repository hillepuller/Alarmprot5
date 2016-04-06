package com.example.alarmprot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by v on 4/6/16.
 */
public class Alarm_Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent)
    {

        Log.e("Asier", "onReceive: ");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        context.startService(service_intent);
    }
}
