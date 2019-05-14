package com.critics.taste.Fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.critics.taste.MainActivity;
import com.critics.taste.R;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.view_models.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private List<SearchResultEntity> mResults;
    String userSearchQuery;
    String userSearchType;
    String userSearchLimit;

    @Inject
    SearchResultAdapter searchResultAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainActivityViewModel viewModel;

    private EditText editText;
    private Button button;
    private Spinner typeSpinner;
    private Spinner limitSpinner;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = view.findViewById(R.id.search_editText);
        button = view.findViewById(R.id.search_button);
        typeSpinner = view.findViewById(R.id.type_spinner);
        limitSpinner = view.findViewById(R.id.limit_spinner);
        initializeSpinners();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
        button.setOnClickListener(v -> {
            userSearchQuery = editText.getText().toString();
            userSearchType = typeSpinner.getSelectedItem().toString();
            userSearchLimit = limitSpinner.getSelectedItem().toString();

            viewModel.init(userSearchQuery, userSearchType, userSearchLimit);
            viewModel.getSearchResultEntityLiveData()
                    .observe(this, searchResultEntities -> {
                        searchResultAdapter.setItems(searchResultEntities);
                        mResults = searchResultEntities;
                    });
        });
    }

    public void initializeSpinners() {
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.search_result_type
                        , android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeSpinnerAdapter);

        ArrayAdapter<CharSequence> limitSpinnerAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.search_result_limit
                        , android.R.layout.simple_spinner_item);
        limitSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        limitSpinner.setAdapter(limitSpinnerAdapter);
    }
}
