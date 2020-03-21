package com.srit.collegedesigns.activities.login;

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
import com.srit.collegedesigns.activities.home.MainActivity;
import com.srit.collegedesigns.databinding.ActivityLoginBinding;
import com.srit.collegedesigns.helpers.SharedPrefHelper;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;

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

    private boolean validation(){

        if(binding.username.getText().toString().equals("")){
            ViewExtensionsKt.showSnackBar(binding.loginLayout, getString(R.string.mustEnterUsername), true);
            return false;
        }

        if(binding.password.getText().toString().equals("")){
            ViewExtensionsKt.showSnackBar(binding.loginLayout, getString(R.string.mustEnterPassword), true);
            return false;
        }
        return true;

    }

    private void setupViewModel(){
        if(validation()) {
            showProgressIndicator();
            loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            loginViewModel.init(binding.password.getText().toString(), binding.username.getText().toString());
            loginViewModel.getLoginRepository().observe(LoginActivity.this, new Observer<MyResponse>() {
                        @Override
                        public void onChanged(MyResponse myResponse) {
                            hideProgressIndicator();
                            if (myResponse == null) {
                                Log.d("LoginError", "null");
                                return;
                            }
                            if (myResponse.getError() == null) {
                                MainActivity.newInstance(LoginActivity.this);
                                String token = myResponse.getPosts().get("token").getAsString();
                                SharedPrefHelper.getInstance().setAccessToken(token);
                                Log.d("token", token);
                                finish();
                            } else {
                                // call failed.
                                String s = myResponse.getError();
                                Log.d("LoginError", s);
                                ViewExtensionsKt.showSnackBar(binding.loginLayout, s, true);

                            }
                        }
                    }
            );
        }
    }

    private void initComponent(){
        SharedPrefHelper.init(this);
    }
}
