package com.srit.collegedesigns.activities.section.homework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.activities.section.notification.NotificationAdapter;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.HomeworkFragmentBinding;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;

import java.util.Objects;

public class HomeworkFragment extends Fragment implements HomeworkAdapter.OnImageClick{


    private HomeworkFragmentBinding binding;
    private HomeworkAdapter adapter;
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
        ((SectionActivity) Objects.requireNonNull(getActivity())).setCustomTitle(getString(R.string.homeworks));
        SectionActivity.currentFragment="HomeworkFragment";
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }


    private void setupViewModel() {


        final HomeworkViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked")
            @Override public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(HomeworkViewModel.class)) {
                    return (T) new HomeworkViewModel();
                } else {
                    return null;
                }
            }
        }).get(HomeworkViewModel.class);
        viewModel.init();
        viewModel.getHomeworkRepository().observe(Objects.requireNonNull(this.getActivity()), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("HomeworkError", "null");
                            return;
                        }
                        if (apiResponse.getError() == null) {
                            binding.progressIndicator.setVisibility(View.GONE);
                            adapter = new HomeworkAdapter(getContext(), apiResponse.getPosts());
                            adapter.setLestiner(HomeworkFragment.this);
                            binding.recy.setAdapter(adapter);
                            Log.d("HomeworkSuccess", adapter.getItemCount() + "");
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("HomeworkError", s);
                            ViewExtensionsKt.showSnackBar(binding.homeworkFragment, s, true);

                        }
                    }
                }
        );
    }

    @Override
    public void onImageClick(String src) {
        ImageViewerFragment.newInstance(getFragmentManager(),src);
    }
}
