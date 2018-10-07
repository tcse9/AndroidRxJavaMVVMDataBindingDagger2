package com.example.ovi.rxjavaandroidone.networking;

import com.example.ovi.rxjavaandroidone.core.ApplicationSingleton;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NetworkClient {


    @Inject
    NetworkModule networkModule;

    /**
     * Constructor
     */
    public NetworkClient(){
        //Injecting NetworkModule
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

    }

    /**
     * Returns the Retrofit client
     * @return
     */

    public Retrofit getRetrofit(){
        return networkModule.getClient().getRetrofit();
    }


}
