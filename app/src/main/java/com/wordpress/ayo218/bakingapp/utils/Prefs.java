package com.wordpress.ayo218.bakingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wordpress.ayo218.bakingapp.model.Recipes;

public class Prefs {
    public static final String PREF = "prefs";

    public static void addRecipe(Context context, Recipes recipes) {
        SharedPreferences.Editor preferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit();
        // TODO: 7/3/2018 Come back/ Figure this out
        preferences.putString("widget_key", Recipes.StringBase64(recipes));
        preferences.apply();
    }

    public static Recipes loadRecipe(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String recipe = preferences.getString("widget_key", "");
        return "".equals(recipe) ? null : Recipes.fromBase64(preferences.getString("widget_key", ""));
    }
}
