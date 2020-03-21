package com.srit.collegedesigns.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.ActivityMainBinding;
import com.srit.collegedesigns.helpers.Logout;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;
import com.srit.collegedesigns.activities.home.advertisement.AdvertisementAdapter;
import com.srit.collegedesigns.activities.home.advertisement.AdvertisementViewModel;
import com.srit.collegedesigns.myTypes.Section;
import com.srit.collegedesigns.activities.section.SectionActivity;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    AdvertisementViewModel viewModel;
    AdvertisementAdapter advertisementAdapter;

    public static void newInstance(Context context) {

        Intent in = new Intent(context, MainActivity.class);
        context.startActivity(in);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.progressIndicator.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UserModel userModel = UserModel.getInstance();
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout.logout(MainActivity.this);
            }
        });
        binding.studentName.setText("مرحبا بك " + userModel.getStudentName());
        binding.collegeInfo.setText(userModel.getSection() + " " + userModel.getDivision() + " " + userModel.getStage());
        setupListeners();
        setupViewModel();
    }

    private void setupViewModel() {
        binding.progressIndicator.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(AdvertisementViewModel.class);
        viewModel.init();
        viewModel.getAdvertisementRepository().observe(MainActivity.this, new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("AdvertisementError","null");
                            return;
                        }
                        if (apiResponse.getError() == null) {

                            advertisementAdapter = new AdvertisementAdapter(MainActivity.this, apiResponse.getPosts());
                            binding.recy.setAdapter(advertisementAdapter);
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("AdvertisementError",s);
                            ViewExtensionsKt.showSnackBar(binding.homeLayout, s, true);

                        }
                        binding.progressIndicator.setVisibility(View.GONE);
                    }

                }
        );
    }

    private void setupListeners() {
        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SectionActivity.newInstance(MainActivity.this, Section.NOTIFICATION);
            }
        });
        binding.schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionActivity.newInstance(MainActivity.this, Section.SCHEDULE);
            }
        });
        binding.library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionActivity.newInstance(MainActivity.this, Section.LIBRARY);
            }
        });
        binding.homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionActivity.newInstance(MainActivity.this, Section.HOMEWORK);
            }
        });
    }

}
