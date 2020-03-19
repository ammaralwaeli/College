package com.srit.collegedesigns.activities.section.homework.teacher;

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
import com.srit.collegedesigns.activities.section.schedules.daySchedule.DayModel;
import com.srit.collegedesigns.databinding.ItemLibraryBinding;
import com.srit.collegedesigns.databinding.ItemTeacherBinding;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    private List<DayModel> modelList;
    private Context context;
    private ItemListener itemListener;


    interface ItemListener{
        void onClick();
    }

    TeacherAdapter(Context context, List<DayModel> list) {
        this.context = context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTeacherBinding binding;

        ViewHolder(@NonNull ItemTeacherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind() {
            //binding.setDay(dayModel.getDay());
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.scale);
            animator.setTarget(binding.item);
            animator.start();

            binding.hasPendingBindings();
        }
    }


    @NonNull
    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTeacherBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_teacher, viewGroup, false);
        return new TeacherAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.ViewHolder holder, final int position) {
        holder.bind();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick();
            }
        });
    }

    void setItemListener(ItemListener itemListener){
        this.itemListener=itemListener;
    }
    @Override
    public int getItemCount() {
        return 25;
    }
}
