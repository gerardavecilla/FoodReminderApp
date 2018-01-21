package com.example.gerard.foodreminderapp;


public class FoodItem {
    private String foodName;
    private String foodExpiry;
    public FoodItem(String foodName, String foodExpiry){
        this.foodName = foodName;
        this.foodExpiry = foodExpiry;
    }
    public String getFoodExpiry() {
        return foodExpiry;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodExpiry(String foodExpiry) {
        this.foodExpiry = foodExpiry;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
