package com.srit.collegedesigns.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ItemNotificationBinding;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {



    private Context mContext;

    NotificationAdapter(Context context){
        mContext=context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding binding;

        ViewHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Context context) {
            Animator animator= AnimatorInflater.loadAnimator(context, R.animator.scale);

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
        holder.bind(mContext);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}