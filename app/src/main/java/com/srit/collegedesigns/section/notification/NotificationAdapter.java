package com.srit.collegedesigns.section.notification;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ItemAdvertisementBinding;
import com.srit.collegedesigns.databinding.ItemNotificationBinding;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    List<NotificationModel> modelList;
    Context context;
    public NotificationAdapter(Context context, List<NotificationModel> list) {
        this.context=context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding binding;

        ViewHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(NotificationModel notificationModel) {
            binding.setNotfiaction(notificationModel);
            Animator animator= AnimatorInflater.loadAnimator(context,R.animator.scale);
            animator.setTarget(binding.item);
            animator.start();
            binding.hasPendingBindings();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemNotificationBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_notification, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}