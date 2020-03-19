package com.srit.collegedesigns.helpers;

import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.login.LoginActivity;

public class Logout {

    public static void logout(AppCompatActivity activity){
        SharedPrefHelper.getInstance().setAccessToken(null);
        activity.finishAffinity();
        LoginActivity.newInstance(activity);
    }

    public static void expireToken(ViewGroup contentLayout,AppCompatActivity activity){
        ViewExtensionsKt.showSnackBar(contentLayout, activity.getString(R.string.token_expire),
                true);
        logout(activity);
    }
}
