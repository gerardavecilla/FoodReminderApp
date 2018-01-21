package com.example.gerard.foodreminderapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    public void openDialog(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button button = (Button) findViewById(R.id.button);
        final TextInputLayout textInput1 = (TextInputLayout) findViewById(R.id.textInputLayout);
        final TextInputLayout textInput2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodName = textInput1.getEditText().getText().toString();
                String expirationDate = textInput2.getEditText().getText().toString();
                FoodItem foodInput = new FoodItem(foodName, expirationDate);
                MainActivity.foodList.add(foodInput);

                openDialog(view);
            }
        });
    }
}
