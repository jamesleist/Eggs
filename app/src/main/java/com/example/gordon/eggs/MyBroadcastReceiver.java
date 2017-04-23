package com.example.gordon.eggs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, MyService.class);
        Bundle breakfastBundle = intent.getExtras();
        int number_of_eggs = breakfastBundle.getInt("number_of_eggs");
        if(intent.getAction().equals("com.example.gordon.eggs.MAKE_BREAKFAST")){
            myIntent.putExtra("Eggs", Constants.MAKE_BREAKFAST);
        } else if(number_of_eggs != Constants.NULL_VALUE){
            switch (number_of_eggs){
                case 1:  myIntent.putExtra("Eggs", Constants.ADD_ONE_EGG);
                    break;
                case 2:  myIntent.putExtra("Eggs", Constants.ADD_TWO_EGGS);
                    break;
                case -1: myIntent.putExtra("Eggs", Constants.SUB_ONE_EGG);
                    break;
                default:
                    break;
            }
        }
        context.startService(myIntent);
    }
}
