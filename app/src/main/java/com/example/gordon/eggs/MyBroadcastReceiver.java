package com.example.gordon.eggs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, MyService.class);
        int number_of_eggs = intent.getIntExtra("number_of_eggs", 0);
        if(intent.getAction().equals("com.example.gordon.eggs.MAKE_BREAKFAST")){
            myIntent.putExtra("Eggs", Constants.MAKE_BREAKFAST);
        } else {
            switch (number_of_eggs){
                case Constants.ADD_ONE_EGG:  myIntent.putExtra("Eggs", Constants.ADD_ONE_EGG);
                    break;
                case Constants.ADD_TWO_EGGS:  myIntent.putExtra("Eggs", Constants.ADD_TWO_EGGS);
                    break;
                case Constants.SUB_ONE_EGG: myIntent.putExtra("Eggs", Constants.SUB_ONE_EGG);
                    break;
                default:
                    break;
            }
        }
        context.startService(myIntent);
    }
}
