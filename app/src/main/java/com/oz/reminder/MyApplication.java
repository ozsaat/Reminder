package com.oz.reminder;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

public class MyApplication extends Application {
    private static final int NOTIFICATION_ID = 1;

    private BeaconManager beaconManager;
    private List<Reminder> reminders = emptyList();

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(this);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.d("ASDASD", "ENTERED REAGION");
                if (!reminders.isEmpty()) {
                    showNotification(reminders.get(0));
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d("ASDASD", "LEFT REAGION");
            }
        });

        Log.d("ASDASD", "SETUP");
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region("monitoring region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                                                         2982, 61870));
            }
        });
    }

    public void showNotification(Reminder reminder) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{notifyIntent}, PendingIntent
                .FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(reminder.getTitle())
                .setContentText(reminder.getMessage())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
}
