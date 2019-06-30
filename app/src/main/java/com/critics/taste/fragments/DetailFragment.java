package com.critics.taste.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.critics.taste.R;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.di.AppComponentHelper;
import com.critics.taste.di.component.DaggerDetailFragmentComponent;
import com.critics.taste.di.component.DetailFragmentComponent;
import com.critics.taste.view_models.MainActivityViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";

    Context mContext;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainActivityViewModel viewModel;


    TextView name;
    TextView type;
    TextView teaser;
    TextView url;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        name = view.findViewById(R.id.name);
        type = view.findViewById(R.id.type);
        teaser = view.findViewById(R.id.teaser);
        url = view.findViewById(R.id.url);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //INITIALIZE DAGGER COMPONENT
        DetailFragmentComponent detailFragmentComponent = DaggerDetailFragmentComponent.builder()
                .fragment(this)
                .appComponent(AppComponentHelper.getAppComponent(mContext))
                .build();
        detailFragmentComponent.injectDetailFragment(this);

        //INITIALIZE VIEWMODEL
        viewModel = ViewModelProviders.of(getActivity(), this.viewModelFactory)
                .get(MainActivityViewModel.class);

        viewModel.getSelected().observe(getActivity(), searchResultEntity -> {

            Log.d(TAG,searchResultEntity.getName());
            name.setText(searchResultEntity.getName());
            type.setText(searchResultEntity.getType());
            teaser.setText(searchResultEntity.getWTeaser());
            url.setText(searchResultEntity.getWUrl());
        });
    }
}




















