package com.srit.collegedesigns.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {



    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);

    }
}