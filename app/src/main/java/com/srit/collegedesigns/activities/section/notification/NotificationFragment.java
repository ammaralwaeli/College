package com.srit.collegedesigns.activities.section.notification;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.FragmentNotificationBinding;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;
import com.srit.collegedesigns.myTypes.NotificationType;

import java.util.Objects;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private NotificationAdapter adapter;

    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer, new NotificationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        SectionActivity.currentFragment="NotificationFragment";
        setupRadioGroupLestiner();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel(NotificationType.SECTION);
    }

    private void setupRadioGroupLestiner() {
        binding.toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                binding.progressIndicator.setVisibility(View.VISIBLE);
                if (binding.student.getId() == checkedId) {
                    setupViewModel(NotificationType.STUDENT);
                } else {
                    setupViewModel(NotificationType.SECTION);
                }
            }
        });
    }

    private void setupViewModel(NotificationType notificationType) {


        final NotificationsViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked")
            @Override public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(NotificationsViewModel.class)) {
                    return (T) new NotificationsViewModel();
                } else {
                    return null;
                }
            }
        }).get(NotificationsViewModel.class);
        viewModel.init(notificationType);
        viewModel.getNotificationRepository().observe(Objects.requireNonNull(this.getActivity()), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("NotificationError", "null");
                            return;
                        }
                        if (apiResponse.getError() == null) {
                            binding.progressIndicator.setVisibility(View.GONE);
                            adapter = new NotificationAdapter(getContext(), apiResponse.getPosts());
                            binding.recy.setAdapter(adapter);
                            Log.d("NotificationSuccess", adapter.getItemCount() + "");
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("NotificationError", s);
                            ViewExtensionsKt.showSnackBar(binding.notificationFragment, s, true);

                        }
                    }
                }
        );
    }

}
