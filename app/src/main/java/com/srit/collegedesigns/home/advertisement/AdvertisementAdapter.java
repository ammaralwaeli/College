package com.srit.collegedesigns.home.advertisement;

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

import java.util.List;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.ViewHolder> {

    List<AdvertisemrntModel> modelList;
    Context context;
    public AdvertisementAdapter(Context context,List<AdvertisemrntModel> list) {
        this.context=context;
        this.modelList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAdvertisementBinding binding;

        ViewHolder(@NonNull ItemAdvertisementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AdvertisemrntModel advertisemrntModel) {
            binding.setItem(advertisemrntModel);
            Animator animator= AnimatorInflater.loadAnimator(context,R.animator.scale);
            animator.setTarget(binding.item);
            animator.start();
            binding.hasPendingBindings();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemAdvertisementBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_advertisement, viewGroup, false);
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