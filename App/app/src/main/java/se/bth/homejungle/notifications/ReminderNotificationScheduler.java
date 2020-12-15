package se.bth.homejungle.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * This class sets up a scheduler that is executed once a day at around 8:30.
 * It schedules another intent for exactly 9:00 that publishes the notifications then.
 */
public class ReminderNotificationScheduler extends BroadcastReceiver {

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
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // create the intent that starts the scheduler
        Intent intent = new Intent(context, ReminderNotificationScheduler.class);
        intent.setAction("se.bth.homejungle.SCHEDULE_NOTIFICATION");

        // create a broadcast intent for the AlarmManager
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getFirstSchedulerTime(), 24 * 60 * 60 * 1000, alarmIntent);
    }

    /**
     * The scheduler should be executed every day at 8:30 to schedule notifications for 9:00.
     *
     * @return The time for the first execution of the scheduler (today at 8:30).
     */
    private long getFirstSchedulerTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        return calendar.getTimeInMillis();
    }

    /**
     * Executes the scheduler when it receives the broadcast from the AlarmManager once a day.
     *
     * @param context Context
     * @param intent Intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("NotificationScheduler", "onReceive");
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
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
