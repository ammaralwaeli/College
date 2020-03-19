package com.srit.collegedesigns.activities.section.schedules;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.FragmentSchedulesBinding;
import com.srit.collegedesigns.databinding.ScheduleFragmentBinding;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;
import com.srit.collegedesigns.myTypes.Section;

import java.util.Objects;

public class SchedulesFragment extends Fragment {

    private FragmentSchedulesBinding binding;
    private ScheduleAdapter adapter;
    private static int mid;

    public static void newInstance(FragmentManager fr, int id) {
        mid = id;
        fr.beginTransaction().replace(R.id.fragmentContainer, new SchedulesFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSchedulesBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        ((SectionActivity) Objects.requireNonNull(getActivity())).setCustomTitle(getString(R.string.lessons));
        SectionActivity.currentFragment = "SchedulesFragment";
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }


    private void setupViewModel() {


        final SchedulesViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked")
            @Override
            public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(SchedulesViewModel.class)) {
                    return (T) new SchedulesViewModel();
                } else {
                    return null;
                }
            }
        }).get(SchedulesViewModel.class);
        viewModel.init(SchedulesFragment.mid);
        viewModel.getScheduleRepository().observe(Objects.requireNonNull(this.getActivity()), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("NotificationError", "null");
                            return;
                        }
                        if (apiResponse.getError() == null) {
                            binding.progressIndicator.setVisibility(View.GONE);
                            adapter = new ScheduleAdapter(getContext(), apiResponse.getPosts());
                            binding.recy.setAdapter(adapter);
                            Log.d("ScheduleSuccess", adapter.getItemCount() + "");
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("ScheduleError", s);
                            ViewExtensionsKt.showSnackBar(binding.scheduleDayFragment, s, true);

                        }
                    }
                }
        );
    }
}
