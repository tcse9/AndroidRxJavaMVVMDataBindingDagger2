package com.example.ovi.rxjavaandroidone.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.ovi.rxjavaandroidone.repository.Repository;
import com.example.ovi.rxjavaandroidone.models.Content;

import java.util.List;

public class ContentViewModel extends AndroidViewModel {


    /**
     * View model constructor
     * @param application of type {@link Application}
     */
    public ContentViewModel (Application application){
        super(application);
        init();

    }

    /**
     * Initialization i.e. primary works
     */
    private void init(){
        Repository.getInstance().invokeApiContentList();
    }


    /**
     * Returns the {@link android.arch.lifecycle.LiveData} i.e. content list provided by the server
     * @return
     */
    public MutableLiveData<List<Content>> getContentList() {
        return Repository.getInstance().getContentListLiveData();
    }








}
