package com.example.ovi.rxjavaandroidone.networking;

import com.example.ovi.rxjavaandroidone.models.Content;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("/posts")
    Observable<List<Content>> getContent();
}
