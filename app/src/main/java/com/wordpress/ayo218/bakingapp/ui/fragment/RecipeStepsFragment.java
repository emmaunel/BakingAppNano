package com.wordpress.ayo218.bakingapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.ayo218.bakingapp.R;
import com.wordpress.ayo218.bakingapp.model.Step;
import com.wordpress.ayo218.bakingapp.ui.activity.RecipeStepsDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsFragment extends Fragment {

    @BindView(R.id.instruction_text)
    TextView mTvInstructions;
    public RecipeStepsFragment() { }

    private Step step;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(RecipeStepsDetail.STEP_SELECTED_KEY)){
            step = getArguments().getParcelable(RecipeStepsDetail.STEP_SELECTED_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_steps_fragment, container, false);
        ButterKnife.bind(this, view);

        mTvInstructions.setText(step.getDescription());
        return view;
    }
}
