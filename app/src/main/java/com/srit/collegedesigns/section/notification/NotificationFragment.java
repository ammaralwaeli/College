package com.srit.collegedesigns.section.notification;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.FragmentNotificationBinding;

import java.util.List;

public class NotificationFragment extends Fragment {

    private NotificationsViewModel viewModel;
    FragmentNotificationBinding binding;
    NotificationAdapter adapter;

    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer,new NotificationFragment())
                             .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        setupViewModel();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setupViewModel(){
        viewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        viewModel.init();
        viewModel.getNotificationRepository().observe(this.getActivity(), new Observer<List<NotificationModel>>() {
                    @Override
                    public void onChanged(List<NotificationModel> notificationModels) {
                        try {
                            adapter=new NotificationAdapter(getContext(),notificationModels);
                            binding.recy.setAdapter(adapter);

                        }
                        catch (NullPointerException ex){
                            binding.errorText.setText("Error");
                            ex.printStackTrace();
                        }
                        binding.progressIndicator.setVisibility(View.GONE);
                    }
                }
        );
    }

}
