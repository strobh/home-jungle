package se.bth.homejungle.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This BroadcastReceiver is called when the device was rebooted in order to set up the
 * notification scheduler again.
 *
 * https://stackoverflow.com/questions/12034357/does-alarm-manager-persist-even-after-reboot
 */
public class AlarmAutoStartUp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // setup scheduler on start
        ReminderNotificationScheduler notificationScheduler = new ReminderNotificationScheduler();
        notificationScheduler.setupScheduler(context);
    }
}
