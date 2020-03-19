package com.srit.collegedesigns.activities.section.schedules;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ItemScheduleDayBinding;
import com.srit.collegedesigns.databinding.ItemSchedulesBinding;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleModel> modelList;
    private Context context;


    ScheduleAdapter(Context context, List<ScheduleModel> list) {
        this.context = context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSchedulesBinding binding;

        ViewHolder(@NonNull ItemSchedulesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ScheduleModel dayModel, int pos) {
            binding.setCounter(pos + "");
            binding.setItem(dayModel);
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.scale);
            animator.setTarget(binding.item);
            animator.start();

            binding.hasPendingBindings();
        }
    }


    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemSchedulesBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_schedules, viewGroup, false);
        return new ScheduleAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, final int position) {
        holder.bind(modelList.get(position), position+1);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
