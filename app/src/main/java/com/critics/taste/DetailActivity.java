package com.critics.taste;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.critics.taste.DetailActivityFeature.DaggerDetailActivityComponent;
import com.critics.taste.DetailActivityFeature.DetailActivityComponent;
import com.critics.taste.di.AppComponentHelper;
import com.critics.taste.view_models.DetailActivityViewModel;


import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity {
    TextView textName;
    TextView textType;
    TextView textTeaser;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DetailActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName = findViewById(R.id.textView);
        textType = findViewById(R.id.textType);
        textTeaser = findViewById(R.id.textTeaser);

        //INITIALIZE DAGGER COMPONENT
        DetailActivityComponent detailActivityComponent =
                DaggerDetailActivityComponent.builder()
                        .activity(this)
                        .appcomponent(AppComponentHelper.getAppComponent(this))
                        .build();
        detailActivityComponent.injectDetailActivity(this);

        //INITIALIZE VIEWMODEL
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(DetailActivityViewModel.class);

        //RETRIEVE INTENT EXTRAS(GET SELECTED ROW ID)
        Intent intent = getIntent();
        long rowId = intent.getLongExtra("clickedResultId", 0);

        //RETRIEVE SLECTED RESULT DETAILS
        viewModel.init(rowId);
        viewModel.getSavedResultEntityLiveData().observe(DetailActivity.this, searchResultEntity -> {
            String resultName = searchResultEntity.getName();
            String resultType = searchResultEntity.getType();
            String resultTeaser = searchResultEntity.getWTeaser();

            textName.setText(resultName);
            textType.setText(resultType);
            textTeaser.setText(resultTeaser);
        });


    }
}
