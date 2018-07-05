package com.wordpress.ayo218.bakingapp;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.wordpress.ayo218.bakingapp.idlingResources.RecipeIdlingResources;

public class BaseApplication extends Application {

    private RecipeIdlingResources idlingResources;

    @VisibleForTesting
    private IdlingResource initialize(){
        if (idlingResources == null) {
            idlingResources = new RecipeIdlingResources();
        }
        return idlingResources;
    }

    public BaseApplication(){
        if (BuildConfig.DEBUG) {
            initialize();
        }
    }

    public void setIdleState(boolean state) {
        if (idlingResources != null) {
            idlingResources.setIsdleNow(state);
        }
    }

    public RecipeIdlingResources getIdlingResources() {
        return idlingResources;
    }
}
