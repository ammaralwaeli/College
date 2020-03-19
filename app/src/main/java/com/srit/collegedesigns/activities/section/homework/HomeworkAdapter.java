package com.srit.collegedesigns.activities.section.homework;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;
import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ItemHomeworkBinding;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.ViewHolder> {


    private OnImageClick lestiner;
    private Context context;
    HomeworkAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeworkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_homework, parent, false);
        return new HomeworkAdapter.ViewHolder(binding);
    }

    boolean isImageFitToScreen;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FoldingCell fc = holder.binding.foldingCell;
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });

        final ImageView imageView=holder.binding.myImage;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lestiner.onImageClick(imageView);
            }
        });

        holder.bind("");
    }

    public interface OnImageClick{
        void onImageClick(ImageView image);
    }

    public void setLestiner(OnImageClick listiner){
        this.lestiner=listiner;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeworkBinding binding;

        ViewHolder(@NonNull ItemHomeworkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String s) {
            binding.setItem(s);
            Animator animator= AnimatorInflater.loadAnimator(context,R.animator.scale);
            animator.setTarget(binding.foldingCell);
            animator.start();
            binding.hasPendingBindings();
        }
    }

}
