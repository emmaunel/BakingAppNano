package com.wordpress.ayo218.bakingapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wordpress.ayo218.bakingapp.model.Step;
import com.wordpress.ayo218.bakingapp.ui.activity.RecipeDetailActivity;
import com.wordpress.ayo218.bakingapp.ui.activity.RecipeStepsDetail;
import com.wordpress.ayo218.bakingapp.ui.fragment.RecipeStepsFragment;

import java.util.List;

public class StepsPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Step> steps;

    public StepsPagerAdapter(Context context, List<Step> steps, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RecipeStepsDetail.STEP_SELECTED_KEY, steps.get(position));
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        fragment.setArguments(bundle);
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format("Step %d", position);
    }

    @Override

    public int getCount() {
        return steps.size();
    }
}
