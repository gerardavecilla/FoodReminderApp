package com.example.gerard.foodreminderapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DonationMap extends Activity {
    //implements OnMapReadyCallback
    private static final String TAG = "DonationMap";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    protected ListView mainView;
    protected ArrayAdapter<FoodItem> listAdapter;
    ArrayList<FoodItem> food = new ArrayList<FoodItem>();
    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_map);
        mainView = (ListView)findViewById(R.id.mainView);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //       .findFragmentById(R.id.map);
        // mapFragment.getMapAsync(this);

        FoodItem orange = new FoodItem("Orange", 5);
        FoodItem avocado = new FoodItem("Avocado", 2);
        FoodItem banana = new FoodItem("Banana", 4);

        food.add(orange);
        food.add(avocado);
        food.add(banana);

        for (int i = 0; i < food.size(); i++) {
            String foodName = food.get(i).getName();
            names.add(foodName);
        }
        listAdapter = new ArrayAdapter<FoodItem>(this, R.layout.simplerow,food);

        mainView.setAdapter(listAdapter);


        if (correctVersion()){
            init();
        }
    }

    private void init(){
    }
}
