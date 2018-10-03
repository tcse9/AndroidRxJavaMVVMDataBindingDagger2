package com.example.ovi.rxjavaandroidone.repository;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ovi.rxjavaandroidone.models.Content;
import com.example.ovi.rxjavaandroidone.networking.NetworkClient;
import com.example.ovi.rxjavaandroidone.networking.NetworkInterface;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@Module
public class RepositoryModule {


    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<List<Content>> contentList = new MutableLiveData<>();
    private NetworkClient networkClient;



    /**
     * This method fetches data from server using RxJava and Retrofit
     */
    @Singleton
    @Provides
    public RepositoryModule invokeApiContentList(){
        networkClient = new NetworkClient();

        NetworkInterface networkInterface = networkClient.getRetrofit().create(NetworkInterface.class);

        disposables.add(networkInterface.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));

        return this;

    }

    /**
     * Returns an {@link DisposableObserver} type object
     * @return
     */
    private DisposableObserver<List<Content>> getObserver(){

        return new DisposableObserver<List<Content>>() {
            @Override
            public void onNext(List<Content> contents) {

                contentList.setValue(contents);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ERROR_OVI", ""+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    /**
     * Retunds a live data containing {@link Content} list
     * @return
     */
    @Singleton
    @Provides
    public MutableLiveData<List<Content>> getContentListLiveData() {
        return contentList;
    }
}
