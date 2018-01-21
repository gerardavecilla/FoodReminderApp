package com.example.gerard.foodreminderapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Date;
import java.text.ParseException;
import java.util.Random;
import java.text.SimpleDateFormat;



public class DialogActivity extends AppCompatActivity {

    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "M_CH_ID")
                .setContentTitle("FoodSaver Alert!")
                .setContentText("Something's expired!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, Notifications.class);
        notificationIntent.putExtra(Notifications.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(Notifications.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay*1000*60;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    public void openDialog(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Random rand = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button button = (Button) findViewById(R.id.button);
        final TextInputLayout textInput1 = (TextInputLayout) findViewById(R.id.textInputLayout);
        final TextInputLayout textInput2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(DialogActivity.this)
                        .setSmallIcon(R.drawable.ic_add_item)
                        .setContentTitle("FoodSaver Alert!")
                        .setContentText("Something's expired!");
                notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(DialogActivity.this);
                //notificationManager.notify(1, notificationBuilder.build());
                Runnable runnable = new Runnable(){
                    public void run(){
                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(DialogActivity.this)
                                .setSmallIcon(R.drawable.ic_add_item)
                                .setContentTitle("FoodSaver Alert!")
                                .setContentText("Something's expired!");
                        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(DialogActivity.this);
                        notificationManager.notify(1, notificationBuilder.build());

                    }
                };
                String foodName = textInput1.getEditText().getText().toString();
                String expirationDate = textInput2.getEditText().getText().toString();
                FoodItem foodInput = new FoodItem(foodName, expirationDate);
                MainActivity.foodList.add(foodInput);
                SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                try{
                    date = formatter1.parse(expirationDate);
                }
                catch(ParseException E){

                }
                long now = new Date().getTime();
                long millis = date.getTime();
                if(millis - now < 0){
                    notificationManager.notify(1, notificationBuilder.build());
                    openDialog(view);
                }
                else {
                    Handler handler = new Handler();
                    handler.postDelayed(runnable, millis - now);
                    openDialog(view);
                }

            }
        });
    }
}

