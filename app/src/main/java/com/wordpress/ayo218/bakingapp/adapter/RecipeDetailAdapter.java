package com.wordpress.ayo218.bakingapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.listerner.OnItemClickListener;
import com.wordpress.ayo218.bakingapp.model.Ingredients;
import com.wordpress.ayo218.bakingapp.model.Recipes;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Recipes recipes;
    private OnItemClickListener listener;

    public RecipeDetailAdapter(Recipes recipes, OnItemClickListener  listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ingredients_content, parent, false);
            return new IngredientViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_step_list_item, parent, false);
            return new StepsViewHolder(view);
        }
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IngredientViewHolder){
            IngredientViewHolder viewHolder = (IngredientViewHolder) holder;
            StringBuilder builder =  new StringBuilder();
            for (int i = 0; i < recipes.getIngredients().size(); i++) {
                Ingredients ingredients = recipes.getIngredients().get(i);
                builder.append(String.format(Locale.getDefault(), ". %s (%d %s)",
                        ingredients.getIngredient(), ingredients.getQuantity(), ingredients.getMeasure()));
                builder.append("\n");
            }

            viewHolder.ingredients.setText(builder.toString());
        } else if (holder instanceof StepsViewHolder){
            StepsViewHolder viewHolder = (StepsViewHolder) holder;

            viewHolder.step_number.setText(String.valueOf(position));
            viewHolder.step_name.setText(recipes.getSteps().get(position).getShortDescription());

            holder.itemView.setOnClickListener(view -> listener.onItemClick( holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return recipes.getSteps().size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.steps_number)
        TextView step_number;
        @BindView(R.id.steps_name)
        TextView step_name;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ingredients_txt)
        TextView ingredients;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
