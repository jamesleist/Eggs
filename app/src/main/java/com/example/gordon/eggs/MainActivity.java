package com.example.gordon.eggs;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addOneEgg(View view) {
        makeEggIntent("1");
    }

    public void addTwoEgg(View view) {
        makeEggIntent("2");
    }

    public void subtractOneEgg(View view) {
        makeEggIntent("-1");
    }

    public void makeBreakfast(View view) {
        myIntent = new Intent("com.example.gordon.eggs.MAKE_BREAKFAST");
        sendBroadcast(myIntent);
    }
    private void makeEggIntent(String number_of_eggs){
        myIntent = new Intent("com.example.gordon.eggs.ADD_EGGS");
        myIntent.putExtra("number_of_eggs", number_of_eggs);
        sendBroadcast(myIntent);
    }
}
