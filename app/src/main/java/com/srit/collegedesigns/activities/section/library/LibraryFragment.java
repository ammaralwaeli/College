package com.srit.collegedesigns.activities.section.library;

import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srit.collegedesigns.R;
import com.srit.collegedesigns.activities.section.SectionActivity;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.databinding.LibraryFragmentBinding;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;

import java.util.ArrayList;
import java.util.Objects;

public class LibraryFragment extends Fragment implements LibraryAdapter.ItemListener {

    private LibraryAdapter adapter;
    private LibraryFragmentBinding binding;

    public static void newInstance(FragmentManager fr) {
        fr.beginTransaction().add(R.id.fragmentContainer, new LibraryFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LibraryFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        ((SectionActivity) Objects.requireNonNull(getActivity())).setCustomTitle(getString(R.string.library));
        SectionActivity.currentFragment="LibraryFragment";
        binding.recy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
    }


    private void setupViewModel() {


        final LibraryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked")
            @Override
            public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(LibraryViewModel.class)) {
                    return (T) new LibraryViewModel();
                } else {
                    return null;
                }
            }
        }).get(LibraryViewModel.class);
        viewModel.init();
        viewModel.getLibraryRepository().observe(Objects.requireNonNull(this.getActivity()), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {

                        if (apiResponse == null) {
                            Log.d("LibraryError", "null");
                            return;
                        }
                        if (apiResponse.getError() == null) {
                            binding.progressIndicator.setVisibility(View.GONE);
                            adapter = new LibraryAdapter(getContext(), apiResponse.getPosts());
                            adapter.setItemListener(LibraryFragment.this);
                            binding.recy.setAdapter(adapter);
                            Log.d("LibrarySuccess", adapter.getItemCount() + "");
                        } else {
                            // call failed.
                            String s = apiResponse.getError();
                            Log.d("LibraryError", s);
                            ViewExtensionsKt.showSnackBar(binding.scheduleDayFragment, s, true);

                        }
                    }
                }
        );
    }

    @Override
    public void onClick(String url) {
        if(url.endsWith(".pdf")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "application/pdf");
            startActivity(intent);
        }else
        {
            ViewExtensionsKt.showSnackBar(binding.scheduleDayFragment, getString(R.string.not_valid_pdf), true);
        }
    }
}
