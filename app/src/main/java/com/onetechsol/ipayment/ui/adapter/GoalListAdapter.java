package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.GoalItemBinding;
import com.onetechsol.ipayment.pojo.GoalModel;

import java.util.List;

public class GoalListAdapter extends RecyclerView.Adapter<GoalListAdapter.GoalHolder> {

    private List<GoalModel> goalModels;

    public void setGoalModels(List<GoalModel> goalModels) {
        this.goalModels = goalModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoalItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goal_item, parent, false);
        return new GoalHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalHolder cardHolder, int position) {

        cardHolder.bind(goalModels.get(position));

    }

    @Override
    public int getItemCount() {
        return goalModels.size();
    }

    public static class GoalHolder extends RecyclerView.ViewHolder {

        private GoalItemBinding goalItemBinding;

        public GoalHolder(GoalItemBinding goalItemBinding) {
            super(goalItemBinding.getRoot());
            this.goalItemBinding = goalItemBinding;
        }


        public void bind(GoalModel goalModel) {
            goalItemBinding.setGoalModel(goalModel);
            goalItemBinding.executePendingBindings();
        }

    }
}
