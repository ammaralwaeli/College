package com.srit.collegedesigns.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.srit.collegedesigns.adapter.NotificationAdapter;
import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    NotificationAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recy.setAdapter(new NotificationAdapter(this));

        binding.toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (binding.offer.isChecked())
                {
                    Toast.makeText(MainActivity.this, binding.offer.getText().toString(), Toast.LENGTH_LONG).show();
                }
                if(binding.search.isChecked()){
                    Toast.makeText(MainActivity.this, binding.search.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
