package com.wordpress.ayo218.bakingapp.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.adapter.RecipesAdapter;
import com.wordpress.ayo218.bakingapp.api.RecipesApiCallback;
import com.wordpress.ayo218.bakingapp.api.RecipesApiManager;
import com.wordpress.ayo218.bakingapp.listerner.OnItemClickListener;
import com.wordpress.ayo218.bakingapp.model.Recipes;
import com.wordpress.ayo218.bakingapp.ui.activity.RecipeDetailActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";
    @BindView(R.id.main_recipes_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout refreshLayout;

    private List<Recipes> recipesList;

    private BroadcastReceiver networkChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (recipesList == null) {
                Log.e(TAG, "onReceive: Here" );
                loadRecipes();
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_list, container, false);
        ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(() -> loadRecipes());

        initView();
        return view;
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        // FIXME: 7/2/2018 ALso did this
        boolean twoPane = getResources().getBoolean(R.bool.twoPaneMode);
        if (twoPane){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else{
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

    private static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void loadRecipes(){
        // TODO: 6/30/2018 Add a loading animation
        if (isConnected(Objects.requireNonNull(getContext()))){
            refreshLayout.setRefreshing(true);
            RecipesApiManager.getInstance().getRecipes(new RecipesApiCallback<List<Recipes>>() {
                @Override
                public void onResponse(List<Recipes> result) {
                    Log.e(TAG, "onResponse: I got something");
                    if (result != null) {
                        Log.e(TAG, "onResponse: Here" );
                        refreshLayout.setRefreshing(false);
                        recipesList = result;
                        recyclerView.setAdapter(new RecipesAdapter(getContext(), recipesList, new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
                                intent.putExtra(RecipeDetailActivity.RECIPE_KEY, recipesList.get(position));
                                startActivity(intent);
                            }
                        }));
                    }
                }

                @Override
                public void onCancel() {

                }
            });
        } else {
            Snackbar.make(getView(), "No Connection", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(networkChange, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(networkChange);
    }
}
