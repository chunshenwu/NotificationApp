package com.example.jason.notifyapp;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import notification.NotificationControl;
import shortcuts.ShortcutControl;
import widget.WidgetControl;

public class MainActivity extends AppCompatActivity {

    //View
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb = findViewById(R.id.checkBox);
        if (WidgetControl.getIns(getApplicationContext()).isAnyEnable()) {
            cb.setChecked(true);
        }
    }

    public void Send_Notification(View view) {
        NotificationControl.getIns(view.getContext()).sendNotification(view);
    }

    public void Clear_Notification(View view) {
        NotificationControl.getIns(view.getContext()).cleanAll();
        Toast.makeText(getApplicationContext(), "Clear_Notification", Toast.LENGTH_SHORT).show();
    }

    public void Widget(View view) {
        if (cb.isChecked()) {
            WidgetControl.enable(view.getContext());
        } else {
            WidgetControl.disable(view.getContext());
        }
    }

    public void Create_Shortcut(View view) {
        String id = ShortcutControl.getIns(view.getContext()).addShortcuts();
        Toast.makeText(getApplicationContext(), "Create_Shortcut : " + id, Toast.LENGTH_SHORT).show();
    }

    public void Clear_Shortcut(View view) {
        ShortcutControl.getIns(view.getContext()).clear();
        Toast.makeText(getApplicationContext(), "Clear_Shortcut", Toast.LENGTH_SHORT).show();
    }
}
