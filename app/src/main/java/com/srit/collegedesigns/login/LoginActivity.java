package com.srit.collegedesigns.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
                loginViewModel.init(binding.password.getText().toString(),binding.username.getText().toString());
                loginViewModel.getLoginRepository().observe(LoginActivity.this, new Observer<JsonObject>() {
                            @Override
                            public void onChanged(JsonObject jsonObject) {
                                try {
                                    Log.d("token",jsonObject.get("token").getAsString());
                                }
                                catch (NullPointerException ex){
                                    ex.printStackTrace();
                                }
                            }
                        }
                );
            }
        });
    }



}
