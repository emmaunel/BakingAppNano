package com.wordpress.ayo218.bakingapp.model;

public class Ingredients {
    private int quantity;
    private String measure;
    private String ingredient;


    public Ingredients(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
