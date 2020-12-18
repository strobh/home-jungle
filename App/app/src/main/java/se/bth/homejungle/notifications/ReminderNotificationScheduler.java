package se.bth.homejungle.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * This class sets up a scheduler that is executed the next day at 9:00 that publishes the
 * notifications then.
 */
public class ReminderNotificationScheduler {

    /**
     * Sets up the scheduler such that it is executed once a day.
     *
     * The scheduler is executed once a day and checks whether a reminder notification has to be
     * scheduled for the current day.
     *
     * @param context Context
     */
    public void setupScheduler(Context context) {
        Log.v("NotificationScheduler", "setupScheduler");
        scheduleNotification(context);
    }

    private void scheduleNotification(Context context) {
        Intent publisherIntent = new Intent(context, ReminderNotificationPublisher.class);
        publisherIntent.setAction("se.bth.homejungle.PUBLISH_NOTIFICATION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, publisherIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, getReminderNotificationTime(), pendingIntent);
    }

    private long getReminderNotificationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }
}
