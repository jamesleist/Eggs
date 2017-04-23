package com.example.gordon.eggs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addOneEgg(View view) {
        makeEggIntent(Constants.ADD_ONE_EGG);
    }

    public void addTwoEggs(View view) {
        makeEggIntent(Constants.ADD_TWO_EGGS);
    }

    public void subtractOneEgg(View view) {
        makeEggIntent(Constants.SUB_ONE_EGG);
    }

    public void makeBreakfast(View view) {
        myIntent = new Intent("com.example.gordon.eggs.MAKE_BREAKFAST");
        Toast.makeText(this, "This is my Toast message!",
                Toast.LENGTH_LONG).show();
        sendBroadcast(myIntent);
    }

    private void makeEggIntent(int number_of_eggs){
        myIntent = new Intent("com.example.gordon.eggs.ADD_EGGS");
        myIntent.putExtra("number_of_eggs", number_of_eggs);
        sendBroadcast(myIntent);
    }
}
