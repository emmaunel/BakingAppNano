package com.wordpress.ayo218.bakingapp.api;

public interface RecipesApiCallback<T> {

    void onResponse(T result);
    void onCancel();
}
