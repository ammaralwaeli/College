package com.srit.collegedesigns.activities.section.homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.HomeworkFragmentBinding;

public class HomeworkFragment extends Fragment {


    private HomeworkFragmentBinding binding;
    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().replace(R.id.fragmentContainer, new HomeworkFragment())
                .addToBackStack(null)
                .commit();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeworkFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.progressIndicator.setVisibility(View.GONE);
        binding.recy.setAdapter(new HomeworkAdapter(getContext()));
    }

}
