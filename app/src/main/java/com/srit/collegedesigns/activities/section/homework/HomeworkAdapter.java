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
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.ViewHolder> {

    private OnImageClick lestiner;
    private Context context;
    private List<HomeworkModel> homeworkModelList;
    HomeworkAdapter(Context context,List<HomeworkModel> list){
        this.context=context;
        this.homeworkModelList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeworkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_homework, parent, false);
        return new HomeworkAdapter.ViewHolder(binding);
    }

    boolean isImageFitToScreen;
    int i;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FoldingCell fc = holder.binding.foldingCell;
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });

        final String src=homeworkModelList.get(position).getImg();
        final ImageView imageView=holder.binding.myImage;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lestiner.onImageClick(src);
            }
        });

        holder.bind(homeworkModelList.get(position));
    }

    public interface OnImageClick{
        void onImageClick(String image);
    }

    public void setLestiner(OnImageClick listiner){
        this.lestiner=listiner;
    }

    @Override
    public int getItemCount() {
        return homeworkModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeworkBinding binding;

        ViewHolder(@NonNull ItemHomeworkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(HomeworkModel model) {
            binding.setItem(model);

            Animator animator= AnimatorInflater.loadAnimator(context,R.animator.scale);
            animator.setTarget(binding.foldingCell);
            animator.start();
            binding.hasPendingBindings();
        }
    }

}
