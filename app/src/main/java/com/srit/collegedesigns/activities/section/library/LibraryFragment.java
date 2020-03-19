package com.srit.collegedesigns.activities.section.library;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.schedules.daySchedule.DayModel;
import com.srit.collegedesigns.databinding.LibraryFragmentBinding;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private LibraryViewModel mViewModel;
    private LibraryFragmentBinding binding;
    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer, new LibraryFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LibraryFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.recy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.progressIndicator.setVisibility(View.GONE);
        binding.recy.setAdapter(new LibraryAdapter(getContext(),new ArrayList<DayModel>()));
    }

}
