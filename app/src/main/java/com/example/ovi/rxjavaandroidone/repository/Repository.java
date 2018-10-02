package com.example.ovi.rxjavaandroidone.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ovi.rxjavaandroidone.models.Content;
import com.example.ovi.rxjavaandroidone.networking.NetworkClient;
import com.example.ovi.rxjavaandroidone.networking.NetworkInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {


    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<List<Content>> contentList = new MutableLiveData<>();
    private static Repository instance;

    /**
     * Private constructor
     */
    private Repository(){

    }


    /**
     * Instantiate an instance of {@link Repository} as SingleTone
     * @return
     */
    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }

        return instance;
    }


    /**
     * This method fetches data from server using RxJava and Retrofit
     */
    public void invokeApiContentList(){

        NetworkInterface networkInterface = NetworkClient.getInstance().getRetrofit().create(NetworkInterface.class);

        disposables.add(networkInterface.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));

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
    public MutableLiveData<List<Content>> getContentListLiveData() {
        return contentList;
    }
}
