package com.example.ovi.rxjavaandroidone.networking;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {


    public static Retrofit retrofit;
    private static NetworkClient instance;

    /**
     * Private constructor for SingleTon purpose uses
     */
    private void NetworkClient(){

    }

    /**
     * Instantiate an instance of {@link NetworkClient}
     * @return
     */
    public static NetworkClient getInstance(){
        if(instance == null)
            instance = new NetworkClient();

        return instance;
    }


    /**
     * Returns a Retrofit api client
      * @return
     */
    public  Retrofit getRetrofit(){

        if(retrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

        return retrofit;
    }
}
