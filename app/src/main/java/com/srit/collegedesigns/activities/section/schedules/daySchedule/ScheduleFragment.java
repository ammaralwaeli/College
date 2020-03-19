package com.srit.collegedesigns.activities.section.schedules.daySchedule;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.activities.section.schedules.SchedulesFragment;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.ScheduleFragmentBinding;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ScheduleFragment extends Fragment implements ScheduleDayAdapter.ItemListener {

    private ScheduleFragmentBinding binding;
    private ScheduleDayAdapter adapter;

    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer, new ScheduleFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ScheduleFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.recy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ((SectionActivity) Objects.requireNonNull(getActivity())).setCustomTitle(getString(R.string.schedule));
        SectionActivity.currentFragment = "ScheduleFragment";
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();

    }


    private void setupViewModel() {


        final ScheduleViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {

            @SuppressWarnings("unchecked")
            @Override
            public <T extends ViewModel> T create(@NotNull final Class<T> modelClass) {
                if (modelClass.equals(ScheduleViewModel.class)) {
                    return (T) new ScheduleViewModel();
                } else {
                    return null;
                }
            }
        }).get(ScheduleViewModel.class);
        viewModel.init();
        viewModel.getScheduleRepository().observe(Objects.requireNonNull(this.getActivity()), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("NotificationError", "null");
                            return;
                        }
                        if (apiResponse.getError() == null) {
                            binding.progressIndicator.setVisibility(View.GONE);
                            adapter = new ScheduleDayAdapter(getContext(), apiResponse.getPosts());
                            adapter.setItemListener(ScheduleFragment.this);
                            binding.recy.setAdapter(adapter);
                            Log.d("ScheduleDaySuccess", adapter.getItemCount() + "");
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("ScheduleDayError", s);
                            ViewExtensionsKt.showSnackBar(binding.scheduleDayFragment, s, true);

                        }
                    }
                }
        );
    }

    @Override
    public void onClick(int id) {
        assert getFragmentManager() != null;
        SchedulesFragment.newInstance(getFragmentManager(), id);

    }
}
