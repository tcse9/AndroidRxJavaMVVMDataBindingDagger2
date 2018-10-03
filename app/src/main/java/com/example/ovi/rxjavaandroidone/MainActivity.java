package com.example.ovi.rxjavaandroidone;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.ovi.rxjavaandroidone.adapters.MoviesAdapter;
import com.example.ovi.rxjavaandroidone.binders.UiManager;
import com.example.ovi.rxjavaandroidone.databinding.ActivityMainRootBinding;
import com.example.ovi.rxjavaandroidone.models.Content;
import com.example.ovi.rxjavaandroidone.viewmodels.ContentViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter moviesAdapter;
    private ContentViewModel contentViewModel;
    private ActivityMainRootBinding binding;
    private UiManager uiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize dagger
        //ApplicationSingleton.getInstance().getBaseComponents().inject(this);

        //Initializing data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_root);
        uiManager = new UiManager();
        binding.setUimanager(uiManager);
        setSupportActionBar(binding.toolbar);



        //Initializing viewmodel
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        //Activity view related initialization and action
        initView();
        initAction();
    }

    /**
     * This method initialize views and view related secondary works
     */
    private void initView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    /**
     * This method handles actions on  views
     */
    private void initAction(){

        contentViewModel.getContentList().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contents) {
                if (contents != null) {
                    moviesAdapter = new MoviesAdapter(contents,MainActivity.this);
                    binding.recyclerView.setAdapter(moviesAdapter);
                    uiManager.setLoadingProgressBarVisibility(View.GONE);
                }
            }
        });


    }




}
