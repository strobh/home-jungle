package se.bth.homejungle.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReminderNotificationPublisher extends BroadcastReceiver {

    public static final String NOTIFICATION_CHANNEL_ID = "se.bth.homejungle.notifications.channel";
    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("ReminderNotificationPublisher", "onReceive");
        final PendingResult result = goAsync();
        new PublishNotificationTask(context).execute(result);
    }
}
