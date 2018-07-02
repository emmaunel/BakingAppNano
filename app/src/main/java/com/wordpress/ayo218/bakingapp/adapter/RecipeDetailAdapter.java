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
import com.wordpress.ayo218.bakingapp.model.Recipes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder> {

    private Recipes recipes;
    private OnItemClickListener listener;

    public RecipeDetailAdapter(Recipes recipes, OnItemClickListener  listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.recipe_step_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.step_number.setText(String.valueOf(position - 1) + ".");

        // TODO: 7/1/2018 Come back
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick( holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.getIngredients().size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.steps_number)
        TextView step_number;
        @BindView(R.id.steps_name)
        TextView step_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
