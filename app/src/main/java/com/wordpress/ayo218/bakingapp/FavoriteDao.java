package com.wordpress.ayo218.bakingapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.wordpress.ayo218.bakingapp.model.Recipes;

@Dao
public interface FavoriteDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipes recipes);
}
