package com.example.gordon.eggs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;

public class MyService extends Service {
    private SharedPreferences.Editor editor;
    private int eggNumber;

    public int onStartCommand(Intent intent, int flags, int startId) {

        //Using SharedPrefences to store egg number
        SharedPreferences prefs = getApplicationContext().
                getSharedPreferences("com.example.gordon.eggs", MODE_PRIVATE);

        editor = prefs.edit();

        //Working egg amount
        eggNumber = prefs.getInt("eggs", 0);


        //Check egg value, do work based on it
        Bundle myBundle = intent.getExtras();
        switch(myBundle.getInt("Eggs")){
            case Constants.ADD_ONE_EGG:
                eggNumber = Constants.ADD_ONE_EGG + eggNumber;
                updateSharedPreference();
                addEggsNotification("R.strings.add1");
                break;
            case Constants.ADD_TWO_EGGS:
                eggNumber = Constants.ADD_TWO_EGGS + eggNumber;
                updateSharedPreference();
                addEggsNotification("R.strings.add2");
                break;
            case Constants.SUB_ONE_EGG:
                eggNumber = (eggNumber == Constants.NULL_VALUE) ? eggNumber : Constants.SUB_ONE_EGG + eggNumber;
                updateSharedPreference();
                addEggsNotification("R.strings.subtract1");
                break;
            case Constants.MAKE_BREAKFAST:
                if(eggNumber < Constants.NUM_EGGS_OMELET){
                    eggNumber = 0;
                    updateSharedPreference();
                    makeBreakfastNotification(false);
                } else {
                    eggNumber = eggNumber - Constants.NUM_EGGS_OMELET;
                    updateSharedPreference();
                    makeBreakfastNotification(true);
                }
                break;
            default:
                break;
        }

        stopSelf(startId);

        return START_NOT_STICKY;
    }

    private void updateSharedPreference(){
        editor.putInt("eggs", eggNumber);
        editor.apply();
    }

    private void addEggsNotification(String notificationMessage){
        doNotification(notificationMessage);
    }

    private void makeBreakfastNotification(boolean enough_for_omelet){
        String notificationMessage = (enough_for_omelet) ? ("R.strings.breakfast_omelet") : ("R.strings.breakfast_gruel");
        notificationMessage += eggNumber + "R.strings.eggs_available";
        doNotification(notificationMessage);
    }


    private int MYNOTIFICATION = 1;
    public void doNotification(String description) {
        boolean useIndeterminateProgressBar = false;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(description)
                .setSmallIcon(R.drawable.egg)
                .setOngoing(false)                        //true only dismissable by app
                .build();
        noti.flags |= Notification.FLAG_INSISTENT;
        notificationManager.notify(MYNOTIFICATION, noti);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}