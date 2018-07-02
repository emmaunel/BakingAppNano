package com.wordpress.ayo218.bakingapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.adapter.RecipeDetailAdapter;
import com.wordpress.ayo218.bakingapp.listerner.OnItemClickListener;
import com.wordpress.ayo218.bakingapp.model.Recipes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipes";

    @BindView(R.id.recipe_step_list_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    private Recipes recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(RECIPE_KEY)){
            recipes = getIntent().getExtras().getParcelable(RECIPE_KEY);
        } else {
            Snackbar.make(getCurrentFocus(), "NO DATA AVAILABLE AT THE MOMENT", Snackbar.LENGTH_LONG).show();
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(recipes.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initView();
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipeDetailAdapter(recipes, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openStepDetail(position);
            }
        }));
    }


    private void openStepDetail(int position) {
        Intent intent = new Intent(this, RecipeStepsDetail.class);
        intent.putExtra(RECIPE_KEY, recipes);
        intent.putExtra(RecipeStepsDetail.STEP_SELECTED_KEY, position);
        startActivity(intent);
    }
}
