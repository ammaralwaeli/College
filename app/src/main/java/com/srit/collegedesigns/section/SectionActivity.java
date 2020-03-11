package com.srit.collegedesigns.section;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.databinding.ActivitySectionBinding;
import com.srit.collegedesigns.home.MainActivity;
import com.srit.collegedesigns.myTypes.Section;
import com.srit.collegedesigns.section.notification.NotificationFragment;

import java.util.Objects;

public class SectionActivity extends AppCompatActivity {

    ActivitySectionBinding binding;

    private static String EXTRA_SECTION="extraSection";
    public static void newInstance(Context context, Section section) {

        Intent in = new Intent(context, SectionActivity.class);
        in.putExtra(EXTRA_SECTION,section);
        context.startActivity(in);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_section);
        createFragment();
    }


    private void createFragment(){
        switch ((Section) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_SECTION))){
            case NOTIFICATION:
                binding.setTitle(getString(R.string.notifications));
                NotificationFragment.newInstance(getSupportFragmentManager());
        }
    }

}
