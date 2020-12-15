package se.bth.homejungle.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

import java.lang.ref.WeakReference;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import se.bth.homejungle.MainActivity;
import se.bth.homejungle.R;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.CalendarManager;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.entity.view.CalendarEventType;

import static se.bth.homejungle.notifications.ReminderNotificationPublisher.NOTIFICATION_CHANNEL_ID;
import static se.bth.homejungle.notifications.ReminderNotificationPublisher.NOTIFICATION_ID;

public class PublishNotificationTask extends AsyncTask<BroadcastReceiver.PendingResult, Void, Void> {
    private WeakReference<Context> contextRef;

    public PublishNotificationTask(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(BroadcastReceiver.PendingResult... pendingResults) {
        Log.v("ReminderNotificationPublisher", "doInBackground");

        // get and check context
        Context context = contextRef.get();
        if (context == null) {
            return null;
        }

        // get and check calendar events
        List<CalendarEvent> calendarEvents = getCalendarEvents(context);
        if (calendarEvents.size() == 0) {
            return null;
        }

        // get and check notification manager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (notificationManager == null) {
            return null;
        }

        // create notification channel
        Notification notification = createReminderNotification(context, calendarEvents);

        // create and publish notification
        createNotificationChannel(context);
        notificationManager.notify(NOTIFICATION_ID, notification);

        pendingResults[0].finish();
        return null;
    }

    private List<CalendarEvent> getCalendarEvents(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        CalendarManager calendarManager = database.getCalendarManager();
        List<CalendarEvent> calendarEvents = calendarManager.getCalendarEventsSynchronously();
        calendarEvents = calendarEvents.stream()
                .filter(calendarEvent -> (calendarEvent.getDate().isEqual(LocalDate.now()) || calendarEvent.getDate().isBefore(LocalDate.now())))
                .collect(Collectors.toList());
        return calendarEvents;
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "Home Jungle";
            String description = "Notifications from Home Jungle";

            // create the notification channel
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            // register the channel
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification createReminderNotification(Context context, List<CalendarEvent> calendarEvents) {
        String text = buildNotificationText(calendarEvents);

        // when clicking on the notification the user is navigated to the calendar fragment
        PendingIntent calendarIntent = (new NavDeepLinkBuilder(context))
                .setComponentName(MainActivity.class)
                .setGraph(R.navigation.mobile_navigation)
                .setDestination(R.id.navigation_calendar)
                .createPendingIntent();

        // create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_flower)
                .setContentTitle("Reminder")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(calendarIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

        return builder.build();
    }

    private String buildNotificationText(List<CalendarEvent> calendarEvents) {
        long countWater = calendarEvents.stream().filter(calendarEvent -> calendarEvent.getType() == CalendarEventType.WATER).count();
        long countPlant = calendarEvents.stream().filter(calendarEvent -> calendarEvent.getType() == CalendarEventType.PLANT).count();

        String textWater = "";
        if (countWater == 1) {
            textWater = "1 plant";
        } else if (countWater > 1) {
            textWater = countWater + " plants";
        }

        String textPlant = "";
        if (countPlant == 1) {
            textPlant = "1 new plant";
        } else if (countPlant > 1) {
            textPlant = countPlant + " new plants";
        }

        String text = "";
        if (countWater > 0 && countPlant > 0) {
            text = "You have to water " + textWater + " and plant " + textPlant + " today.";
        } else if (countWater > 0) {
            text = "You have to water " + textWater + " today.";
        } else if (countPlant > 0) {
            text = "You have to plant " + textPlant + " today.";
        }
        return text;
    }
}
