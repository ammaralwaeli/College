package com.srit.collegedesigns.activities.section.schedules.daySchedule;

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

import java.util.List;

public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.ViewHolder> {

    private List<DayModel> modelList;
    private Context context;
    private ItemListener itemListener;


    interface ItemListener{
        void onClick(int id);
    }

    ScheduleDayAdapter(Context context, List<DayModel> list) {
        this.context = context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemScheduleDayBinding binding;

        ViewHolder(@NonNull ItemScheduleDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DayModel dayModel) {
            binding.setDay(dayModel.getDay());
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.scale);
            animator.setTarget(binding.item);
            animator.start();

            binding.hasPendingBindings();
        }
    }


    @NonNull
    @Override
    public ScheduleDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemScheduleDayBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_schedule_day, viewGroup, false);
        return new ScheduleDayAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleDayAdapter.ViewHolder holder, final int position) {
        holder.bind(modelList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick(modelList.get(position).getId());
            }
        });
    }

    void setItemListener(ItemListener itemListener){
        this.itemListener=itemListener;
    }
    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
