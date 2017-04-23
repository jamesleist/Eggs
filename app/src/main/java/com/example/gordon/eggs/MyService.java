package com.example.gordon.eggs;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    private SharedPreferences.Editor editor;
    private int eggNumber;

    public int onStartCommand(Intent intent, int flags, int startId) {

        //Using SharedPrefences to store egg number
        SharedPreferences prefs = getApplicationContext().
                getSharedPreferences("com.example.gordon.eggs", MODE_PRIVATE);

        editor = prefs.edit();

        //Ensures that the stored egg count is initialized to zero
        //if(prefs.getInt("eggs", 0) == 0) {
        //    editor.putInt("eggs", 0);
        //    editor.commit();
        //}

        //Working egg amount
        eggNumber = prefs.getInt("eggs", 0);


        //Check egg value
        Bundle myBundle = intent.getExtras();
        switch(myBundle.getInt("Eggs")){
            case Constants.ADD_ONE_EGG:
                eggNumber = Constants.ADD_ONE_EGG + eggNumber;
                updateSharedPreference();
                addEggsNotification();
                break;
            case Constants.ADD_TWO_EGGS:
                eggNumber = Constants.ADD_TWO_EGGS + eggNumber;
                updateSharedPreference();
                addEggsNotification();
                break;
            case Constants.SUB_ONE_EGG:
                eggNumber = (eggNumber == Constants.NULL_VALUE) ? eggNumber : Constants.SUB_ONE_EGG + eggNumber;
                updateSharedPreference();
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

        return START_NOT_STICKY;
    }

    private void updateSharedPreference(){
        editor.putInt("eggs", eggNumber);
        editor.apply();
    }

    private void addEggsNotification(){

    }

    private void makeBreakfastNotification(boolean enough_for_omelet){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}