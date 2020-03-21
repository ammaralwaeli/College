package com.srit.collegedesigns.activities.section.library;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.activities.section.homework.HomeworkRepository;
import com.srit.collegedesigns.api.ApiResponse;

public class LibraryViewModel extends ViewModel {


    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();


    void init() {
        LibraryRepository libraryRepository = LibraryRepository.getInstance();

        mutableLiveData = libraryRepository.getLibrary();

    }

    LiveData<ApiResponse> getLibraryRepository() {
        return mutableLiveData;
    }
}
