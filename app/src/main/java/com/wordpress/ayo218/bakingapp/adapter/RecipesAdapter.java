package com.wordpress.ayo218.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.model.Ingredients;
import com.wordpress.ayo218.bakingapp.model.Recipes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private static final String TAG = "RecipesAdapter";
    private Context context;
    private ArrayList<Recipes> recipesList;
    Recipes recipes;

    public RecipesAdapter(Context context, ArrayList<Recipes> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recipes_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recipe_name.setText(recipesList.get(position).getName());
        holder.recipe_serving.setText(context.getString(R.string.servings, recipesList.get(position).getServings()));

        // FIXME: 6/30/2018
//        Log.e(TAG, "onBindViewHolder: " + recipes.getMeasure());

        String recipes_img = recipesList.get(position).getImage();
        if (!recipes_img.isEmpty()){
            Picasso.get()
                    .load(recipesList.get(position).getImage())
                    .placeholder(R.drawable.ic_dinner)
                    .into(holder.recipe_img);
        }
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recipes_img)
        ImageView recipe_img;
        @BindView(R.id.recipes_name)
        TextView recipe_name;
        @BindView(R.id.recipes_servings)
        TextView recipe_serving;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
