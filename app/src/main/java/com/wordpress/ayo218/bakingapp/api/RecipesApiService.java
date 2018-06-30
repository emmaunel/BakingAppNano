package com.wordpress.ayo218.bakingapp.api;

import com.wordpress.ayo218.bakingapp.model.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesApiService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipes>> getRecipes();
}
