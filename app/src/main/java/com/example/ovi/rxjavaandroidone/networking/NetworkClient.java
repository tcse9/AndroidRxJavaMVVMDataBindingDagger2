package com.example.ovi.rxjavaandroidone.networking;

import com.example.ovi.rxjavaandroidone.core.ApplicationSingleton;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NetworkClient {


    @Inject
    NetworkModule networkModule;

    /**
     * Private constructor for SingleTon purpose uses
     */
    public NetworkClient(){
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

    }

    public Retrofit getRetrofit(){
        return networkModule.getRetrofit();
    }


}
