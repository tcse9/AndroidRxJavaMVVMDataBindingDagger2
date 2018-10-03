package com.example.ovi.rxjavaandroidone.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.ovi.rxjavaandroidone.core.ApplicationSingleton;
import com.example.ovi.rxjavaandroidone.models.Content;
import com.example.ovi.rxjavaandroidone.repository.RepositoryModule;

import java.util.List;

import javax.inject.Inject;

public class ContentViewModel extends AndroidViewModel {

    @Inject
    RepositoryModule repositoryModule;

    /**
     * View model constructor
     * @param application of type {@link Application}
     */
    public ContentViewModel (Application application){
        super(application);

        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

        init();

    }

    /**
     * Initialization i.e. primary works
     */
    private void init(){
        repositoryModule.invokeApiContentList();
    }


    /**
     * Returns the {@link android.arch.lifecycle.LiveData} i.e. content list provided by the server
     * @return
     */
    public MutableLiveData<List<Content>> getContentList() {

        return repositoryModule.getContentListLiveData();
    }








}
