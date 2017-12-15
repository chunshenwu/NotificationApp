package notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ShortcutManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import shortcuts.ShortcutControl;

/**
 * Created by jason on 17-12-15.
 */

public class NotificationControl {

    private static NotificationControl self = null;

    private int[] icons  = {android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_delete,
        android.R.drawable.ic_menu_edit, android.R.drawable.ic_menu_upload, android.R.drawable.ic_menu_camera};

    private Context mContext;
    private NotificationManager mNotificationManager;

    public static NotificationControl getIns(Context context) {
        if (self == null) {
            self = new NotificationControl(context);
        }
        return self;
    }

    private NotificationControl(Context context) {
        mContext = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void sendNotification(View view) {
        int index = mNotificationManager.getActiveNotifications().length + 1;

        String title = "Title : " + (index);
        String message = "message : " + (index);
        //Get an instance of NotificationManager//
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(view.getContext())
                .setSmallIcon(icons[index % icons.length])
                .setContentTitle(title)
                .setContentText(message);
        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than addShortcuts a new one. In this example, the notification’s ID is 001/
        mNotificationManager.notify((int) (Math.random() * Integer.MAX_VALUE), mBuilder.build());

        Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
    }

    public void cleanAll() {
        mNotificationManager.cancelAll();
    }
}
