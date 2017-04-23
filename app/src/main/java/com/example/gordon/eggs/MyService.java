package com.example.gordon.eggs;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    public int onStartCommand(Intent intent, int flags, int startId) {

        //Using SharedPrefences to store egg number
        SharedPreferences prefs = getApplicationContext().
                getSharedPreferences("com.example.gordon.eggs", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        //Ensures that the stored egg count is initialized to zero
        if(prefs.getInt("eggs", 0) == 0) {
            editor.putInt("eggs", 0);
            editor.commit();
        }

        //Working egg amount
        int eggNumber = prefs.getInt("eggs", 0);

        //If the user wants to add eggs
        if(intent.getBundleExtra("EggMode").equals("add_eggs")) {
            int addingEggs = intent.getIntExtra("eggNumber", 0);
            //Make sure that the working amount of eggs never dips below sero
            if(eggNumber + addingEggs < 0) {
                editor.putInt("eggs", 0);
            }

            //Adds eggs
            else {
                editor.putInt("eggs", eggNumber + addingEggs);
            }

            //Stores Egg amount changes to the SharedPreferences
            editor.commit();
        }

        //If user wants to make breakfast
        if(intent.getBundleExtra("EggMode").equals("make_breakfast")) {
            //Make sure the working egg amount is at least six

            if(eggNumber >= 6) {
                editor.putInt("eggs", eggNumber - 6);
                editor.commit();


            }

        }

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}