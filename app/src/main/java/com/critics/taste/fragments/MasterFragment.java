package com.critics.taste.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.navigation.Navigation;

import com.critics.taste.MainActivity;
import com.critics.taste.R;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.di.AppComponentHelper;
import com.critics.taste.di.component.DaggerMasterFragmentComponent;
import com.critics.taste.di.component.MasterFragmentComponent;
import com.critics.taste.view_models.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {
    private static final String TAG = "MasterFragment";

    @Inject
    SearchResultAdapter searchResultAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainActivityViewModel viewModel;

    private List<SearchResultEntity> mResults;

    String userSearchQuery;
    String userSearchType;
    String userSearchLimit;

    EditText searchEditText;
    Spinner searchTypeSpinner;
    Spinner searchLimitSpinner;
    Button searchButton;
    RecyclerView recyclerView;


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        searchEditText = view.findViewById(R.id.search_editText);
        searchTypeSpinner = view.findViewById(R.id.type_spinner);
        searchLimitSpinner = view.findViewById(R.id.limit_spinner);
        initializeSpinners();
        searchButton = view.findViewById(R.id.search_button);
        recyclerView = view.findViewById(R.id.master_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //INITIALIZE DAGGER COMPONENT
        MasterFragmentComponent masterFragmentComponent = DaggerMasterFragmentComponent.builder()
                .fragment(this)
                .appComponent(AppComponentHelper.getAppComponent(getActivity()))
                .build();
        masterFragmentComponent.injectMasterFragment(this);

        //INITIALIZE VIEWMODEL
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(MainActivityViewModel.class);

        //SET RECYCLERVIEW ADAPTER
        recyclerView.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemClickListener(onItemClickListener);

        if (mResults != null) {
            searchResultAdapter.setItems(mResults);
        }

        //SEARCH BUTTON PRESSED
        searchButton.setOnClickListener((View view) -> {
            Log.d("MainActivity", "Search Button Clicked");
            userSearchQuery = searchEditText.getText().toString();
            userSearchType = searchTypeSpinner.getSelectedItem().toString();
            userSearchLimit = searchLimitSpinner.getSelectedItem().toString();


            viewModel.initSearchApi(userSearchQuery, userSearchType, userSearchLimit);
            viewModel.getSearchResultList()
                    .observe(getActivity(), searchResultEntities -> {
                        searchResultAdapter.setItems(searchResultEntities);
                        Log.d(TAG, "set items called");
                        mResults = searchResultEntities;
                    });
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                SearchResultEntity resultEntity = mResults.get(position);
                long clickedResultId = resultEntity.getId();
                viewModel.deleteSavedResult(clickedResultId);
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void initializeSpinners() {
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.search_result_type
                        , android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(typeSpinnerAdapter);

        ArrayAdapter<CharSequence> limitSpinnerAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.search_result_limit
                        , android.R.layout.simple_spinner_item);
        limitSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        searchLimitSpinner.setAdapter(limitSpinnerAdapter);
    }

    //INITIALIZE ITEM CLICK LISTENER
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            SearchResultEntity resultItem = mResults.get(position);
            viewModel.select(resultItem);
            Navigation.findNavController(view)
                    .navigate(R.id.action_masterFragment_to_detailFragment);
        }
    };
}
