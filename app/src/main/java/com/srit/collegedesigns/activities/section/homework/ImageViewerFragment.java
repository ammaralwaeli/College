package com.srit.collegedesigns.activities.section.homework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.databinding.FragmentImageViewerBinding;
import com.srit.collegedesigns.databinding.FragmentNotificationBindingImpl;
import com.srit.collegedesigns.databinding.HomeworkFragmentBinding;

import java.util.Objects;

public class ImageViewerFragment extends Fragment {


    private static String s;
    public static void newInstance(FragmentManager fr,String src) {
        fr.beginTransaction().replace(R.id.fragmentContainer, new ImageViewerFragment())
                .addToBackStack(null)
                .commit();
        s=src;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentImageViewerBinding binding = FragmentImageViewerBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setSrc(s);
        SectionActivity.currentFragment = "ImageViewerFragment";
        return binding.getRoot();
    }
}
