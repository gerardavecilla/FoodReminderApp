package com.example.gerard.foodreminderapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<FoodItem>{


    public UsersAdapter(@NonNull Context context, @NonNull List<FoodItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FoodItem foodItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_view, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.text);
        TextView expiration = (TextView) convertView.findViewById(R.id.expiration);

        name.setText(foodItem.getFoodName());
        //expiration.setText(String.valueOf(foodItem.getFoodExpiry()));

        expiration.setText(foodItem.getFoodExpiry());

        return convertView;
    }
}
