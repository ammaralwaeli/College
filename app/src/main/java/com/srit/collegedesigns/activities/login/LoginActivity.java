package com.srit.collegedesigns.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivityLoginBinding;
import com.srit.collegedesigns.helpers.SharedPrefHelper;
import com.srit.collegedesigns.activities.home.MainActivity;

public class LoginActivity extends AppCompatActivity {


    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    public static void newInstance(Context context) {

        Intent in = new Intent(context, LoginActivity.class);
        context.startActivity(in);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);

        initComponent();
        if(SharedPrefHelper.getInstance().getAccessToken()!=null){
            MainActivity.newInstance(this);
            finish();
        }
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupViewModel();
            }
        });
    }

    private void showProgressIndicator(){
        binding.loginbtn.setText("");
        binding.progressIndicator.setVisibility(View.VISIBLE);
    }

    private void hideProgressIndicator(){
        binding.loginbtn.setText(getString(R.string.login));
        binding.progressIndicator.setVisibility(View.GONE);
    }
    private void setupViewModel(){
        showProgressIndicator();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init(binding.password.getText().toString(),binding.username.getText().toString());
        loginViewModel.getLoginRepository().observe(LoginActivity.this, new Observer<JsonObject>() {
                    @Override
                    public void onChanged(JsonObject jsonObject) {
                        hideProgressIndicator();
                        try {
                            MainActivity.newInstance(LoginActivity.this);
                            SharedPrefHelper.getInstance().setAccessToken(jsonObject.get("token").getAsString());
                            Log.d("token",jsonObject.get("token").getAsString());
                            finish();
                        }
                        catch (NullPointerException ex){
                            ex.printStackTrace();
                        }
                    }
                }
        );
    }

    private void initComponent(){
        SharedPrefHelper.init(this);
    }
}
