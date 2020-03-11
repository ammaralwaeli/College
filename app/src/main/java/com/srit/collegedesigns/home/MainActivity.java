package com.srit.collegedesigns.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivityMainBinding;
import com.srit.collegedesigns.helpers.Logout;
import com.srit.collegedesigns.home.advertisement.AdvertisementAdapter;
import com.srit.collegedesigns.home.advertisement.AdvertisementViewModel;
import com.srit.collegedesigns.home.advertisement.AdvertisemrntModel;
import com.srit.collegedesigns.myTypes.Section;
import com.srit.collegedesigns.section.SectionActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    AdvertisementViewModel viewModel;
    AdvertisementAdapter advertisementAdapter;
    public static void newInstance(Context context) {

        Intent in = new Intent(context, MainActivity.class);
        context.startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.progressIndicator.setVisibility(View.VISIBLE);
        UserModel userModel=UserModel.getInstance();
        binding.studentName.setText("مرحبا بك "+userModel.getStudentName());
        binding.collegeInfo.setText(userModel.getSection() + " " + userModel.getDivision() + " " + userModel.getStage());
        setupListeners();
        setupViewModel();
    }
    private void setupViewModel(){
        viewModel = new ViewModelProvider(this).get(AdvertisementViewModel.class);
        viewModel.init();
        viewModel.getAdvertisementRepository().observe(MainActivity.this, new Observer<List<AdvertisemrntModel>>() {
                    @Override
                    public void onChanged(List<AdvertisemrntModel> advertisemrntModels) {
                        try {
                            binding.progressIndicator.setVisibility(View.GONE);
                            advertisementAdapter=new AdvertisementAdapter(MainActivity.this,advertisemrntModels);
                            binding.recy.setAdapter(advertisementAdapter);
                        }
                        catch (NullPointerException ex){
                            ex.printStackTrace();
                        }
                    }
                }
        );
    }

    private void setupListeners(){
        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SectionActivity.newInstance(MainActivity.this,Section.NOTIFICATION);
            }
        });
    }

}
