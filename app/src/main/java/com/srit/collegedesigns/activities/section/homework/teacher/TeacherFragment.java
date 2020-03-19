package com.srit.collegedesigns.activities.section.homework.teacher;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.homework.HomeworkFragment;
import com.srit.collegedesigns.activities.section.library.LibraryAdapter;
import com.srit.collegedesigns.activities.section.library.LibraryFragment;
import com.srit.collegedesigns.activities.section.schedules.daySchedule.DayModel;
import com.srit.collegedesigns.databinding.LibraryFragmentBinding;
import com.srit.collegedesigns.databinding.TeacherFragmentBinding;

import java.util.ArrayList;

public class TeacherFragment extends Fragment {

    private TeacherViewModel mViewModel;
    private TeacherFragmentBinding binding;
    private TeacherAdapter adapter;
    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer, new TeacherFragment())
                .addToBackStack(null)
                .commit();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TeacherFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.recy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.progressIndicator.setVisibility(View.GONE);
        adapter=new TeacherAdapter(getContext(),new ArrayList<DayModel>());
        binding.recy.setAdapter(adapter);
        adapter.setItemListener(new TeacherAdapter.ItemListener() {
            @Override
            public void onClick() {
                assert getFragmentManager() != null;
                HomeworkFragment.newInstance(getFragmentManager());
            }
        });
    }

}
