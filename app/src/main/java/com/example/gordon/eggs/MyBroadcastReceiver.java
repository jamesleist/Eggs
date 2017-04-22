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
        String number_of_eggs = breakfastBundle.getString("number_of_eggs");
        if(intent.getAction().equals("com.example.gordon.eggs.MAKE_BREAKFAST")){
            myIntent.putExtra("Eggs", "make_breakfast");
        } else if(number_of_eggs != null){
            switch (number_of_eggs){
                case "1":  myIntent.putExtra("Eggs", 1);
                    break;
                case "2":  myIntent.putExtra("Eggs", 2);
                    break;
                case "-1": myIntent.putExtra("Eggs", -1);
                    break;
                default:
                    break;
            }
        }
        context.startService(myIntent);
    }
}
