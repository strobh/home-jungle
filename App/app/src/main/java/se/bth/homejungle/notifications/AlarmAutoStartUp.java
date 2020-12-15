package se.bth.homejungle.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmAutoStartUp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // setup scheduler on start
        ReminderNotificationScheduler notificationScheduler = new ReminderNotificationScheduler();
        notificationScheduler.setupScheduler(context);
    }
}
