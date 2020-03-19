package com.srit.collegedesigns.activities.section.library;

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

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private List<DayModel> modelList;
    private Context context;
    private ItemListener itemListener;


    interface ItemListener{
        void onClick(int id);
    }

    LibraryAdapter(Context context, List<DayModel> list) {
        this.context = context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLibraryBinding binding;

        ViewHolder(@NonNull ItemLibraryBinding binding) {
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
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemLibraryBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_library, viewGroup, false);
        return new LibraryAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.ViewHolder holder, final int position) {
        holder.bind();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick(modelList.get(position).getId());
            }
        });
    }

    public void setItemListener(ItemListener itemListener){
        this.itemListener=itemListener;
    }
    @Override
    public int getItemCount() {
        return 25;
    }
}
