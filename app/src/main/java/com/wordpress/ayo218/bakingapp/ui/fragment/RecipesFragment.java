package com.wordpress.ayo218.bakingapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wordpress.ayo218.bakingapp.model.Ingredients;
import com.wordpress.ayo218.bakingapp.utils.Constants;
import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.adapter.RecipesAdapter;
import com.wordpress.ayo218.bakingapp.model.Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";
    @BindView(R.id.main_recipes_recyclerview)
    RecyclerView recyclerView;

    private RecipesAdapter adapter;
    private ArrayList<Recipes> recipesList = new ArrayList<>();
    Ingredients ingredients1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_list, container, false);
        ButterKnife.bind(this, view);
        //Dummy Data
        recipesList.add(new Recipes(1, "Cake", 8, ""));
        recipesList.add(new Recipes(1, "Fry", 8, ""));
        recipesList.add(new Recipes(1, "Onion", 8, ""));
        recipesList.add(new Recipes(1, "Apple", 8, ""));
        loadRecipes();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecipesAdapter(getContext(), recipesList);
        recyclerView.setAdapter(adapter);
    }

    private void loadRecipes() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constants.API, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = response.getJSONObject(i);
                    int id = jsonObject.getInt(Constants.JSON_ID);
                    String name = jsonObject.getString(Constants.JSON_NAME);
                    int servings = jsonObject.getInt(Constants.JSON_SERVINGS);
                    String image = jsonObject.getString(Constants.JSON_IMAGE);

                    JSONArray ingredients = jsonObject.getJSONArray(Constants.JSON_INGREDIENTS);

                    for (int j = 0; j < ingredients.length(); j++) {
                        JSONObject ingredient_obj = ingredients.getJSONObject(j);
                        int quantity = ingredient_obj.getInt(Constants.JSON_INGREDIENTS_QUANTITY);
                        String measure = ingredient_obj.getString(Constants.JSON_INGREDIENTS_MEASURE);
                        String ingredient = ingredient_obj.getString(Constants.JSON_INGREDIENT);

                        ingredients1 = new Ingredients(quantity, measure, ingredient);
                    }
                    recipesList.add(new Recipes(id, name, servings, image, ingredients1));
                }

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Snackbar.make(getView(), "There has been an error with Json Parsing", Snackbar.LENGTH_LONG).show());
        Volley.newRequestQueue(getContext()).add(request);
    }

}
