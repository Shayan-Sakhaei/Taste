package com.critics.taste;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.critics.taste.MainActivityFeature.DaggerMainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityComponent;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.di.AppComponentHelper;
import com.critics.taste.view_models.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private List<SearchResultEntity> mResults;
    String userSearchQuery;
    String userSearchType;
    String userSearchLimit;

    //INITIALIZE ITEM CLICK LISTENER
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Intent intent;

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            SearchResultEntity resultItem = mResults.get(position);
            intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("resultPosition", position);
            intent.putExtra("query", userSearchQuery);
            intent.putExtra("type", userSearchType);
            intent.putExtra("limit", userSearchLimit);
            startActivity(intent);
        }
    };

    @Inject
    SearchResultAdapter searchResultAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainActivityViewModel viewModel;

    EditText searchEditText;
    Spinner searchTypeSpinner;
    Spinner searchLimitSpinner;
    Button searchButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.launcher_backgorund);

        //INITIALIZE DAGGER COMPONENT
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .activity(this)
                .appcomponent(AppComponentHelper.getAppComponent(this))
                .build();
        mainActivityComponent.injectMainActivity(this);

        //INITIALIZE VIEWMODEL
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MainActivityViewModel.class);

        //INITIALIZE VIEWS
        searchEditText = findViewById(R.id.search_editText);
        searchTypeSpinner = findViewById(R.id.type_spinner);
        searchLimitSpinner = findViewById(R.id.limit_spinner);
        initializeSpinners();
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemClickListener(onItemClickListener);

        searchButton.setOnClickListener((View view) -> {
            Log.d("MainActivity", "Search Button Clicked");
            userSearchQuery = searchEditText.getText().toString();
            userSearchType = searchTypeSpinner.getSelectedItem().toString();
            userSearchLimit = searchLimitSpinner.getSelectedItem().toString();

            viewModel.init(userSearchQuery, userSearchType, userSearchLimit);
            viewModel.getSearchResultEntityLiveData()
                    .observe(MainActivity.this, searchResultEntities -> {
                        searchResultAdapter.setItems(searchResultEntities);
                        mResults = searchResultEntities;
                    });


        });
    }

    public void initializeSpinners() {
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.search_result_type
                        , android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(typeSpinnerAdapter);

        ArrayAdapter<CharSequence> limitSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.search_result_limit
                        , android.R.layout.simple_spinner_item);
        limitSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        searchLimitSpinner.setAdapter(limitSpinnerAdapter);
    }
}
