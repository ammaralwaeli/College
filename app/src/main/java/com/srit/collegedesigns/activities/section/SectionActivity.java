package com.srit.collegedesigns.activities.section;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.homework.HomeworkFragment;
import com.srit.collegedesigns.activities.section.homework.teacher.TeacherFragment;
import com.srit.collegedesigns.activities.section.schedules.daySchedule.ScheduleFragment;
import com.srit.collegedesigns.activities.section.library.LibraryFragment;
import com.srit.collegedesigns.databinding.ActivitySectionBinding;
import com.srit.collegedesigns.myTypes.Section;
import com.srit.collegedesigns.activities.section.notification.NotificationFragment;

import java.util.Objects;

public class SectionActivity extends AppCompatActivity {

    public static String currentFragment=null;
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

    public void setCustomTitle(String title){
        binding.setTitle(title);
    }
    private void createFragment(){

        switch ((Section) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_SECTION))){
            case NOTIFICATION:
                setCustomTitle(getString(R.string.notifications));
                NotificationFragment.newInstance(getSupportFragmentManager());
                break;
            case SCHEDULE:
                ScheduleFragment.newInstance(getSupportFragmentManager());
                break;
            case LIBRARY:
                LibraryFragment.newInstance(getSupportFragmentManager());
                break;
            case HOMEWORK:
                HomeworkFragment.newInstance(getSupportFragmentManager());
                break;
        }
    }

    private boolean displayPreviousFragment(String currentFragment)
    {
        Fragment fragment = null;
        switch (currentFragment)
        {
            case "ImageViewerFragment" : fragment = new HomeworkFragment();     break;
            case "SchedulesFragment"    :   fragment = new ScheduleFragment();     break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment);
            ft.commit();
        }
        return fragment==null;
    }

    @Override
    public void onBackPressed() {
        if(displayPreviousFragment(currentFragment)){
            finish();
        }
    }
}
