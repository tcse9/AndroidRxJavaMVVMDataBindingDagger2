package com.example.ovi.rxjavaandroidone.components;

import com.example.ovi.rxjavaandroidone.networking.NetworkClient;
import com.example.ovi.rxjavaandroidone.networking.NetworkModule;
import com.example.ovi.rxjavaandroidone.repository.RepositoryModule;
import com.example.ovi.rxjavaandroidone.viewmodels.ContentViewModel;

import javax.inject.Singleton;

import dagger.Component;


/**
 * {@link BaseComponents} are the components throughout the whole project
 */
@Singleton
@Component (modules = {NetworkModule.class, RepositoryModule.class})
public interface BaseComponents {

    //Inject method foe each of the module where they have been implemented
    void inject(NetworkClient networkClient);
    void inject(ContentViewModel viewModel);

}
