package com.critics.taste;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.critics.taste.MainActivityFeature.DaggerMainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityModule;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.application.TasteApplication;
import com.critics.taste.database.entity.Result;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.di.AppComponentHelper;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.critics.taste.repositories.SearchRepository;
import com.critics.taste.view_models.SearchViewModel;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private List<Result> mResults;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Result resultItem = mResults.get(position);
            Toast.makeText(MainActivity.this, "You Clicked: " + resultItem.getName(), Toast.LENGTH_LONG).show();
        }
    };

    @Inject
    SearchRepository searchRepository;

    @Inject
    TasteDiveWebservice tasteDiveWebservice;

    @Inject
    SearchResultAdapter searchResultAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private SearchViewModel viewModel;

    EditText searchEditText;
    Spinner searchTypeSpinner;
    Spinner searchLimitSpinner;
    Button searchLiveButton;
    Button searchOfflineButton;
    RecyclerView recyclerView;

    String userSearchQuery;
    String userSearchType;
    String userSearchLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INITIALIZE DAGGER COMPONENT
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .activity(this)
                .appComponent(AppComponentHelper.getAppComponent(this))
                .build();
        mainActivityComponent.injectMainActivity(this);

        //INITIALIZE VIEWS
        searchEditText = findViewById(R.id.search_editText);
        searchTypeSpinner = findViewById(R.id.type_spinner);
        searchLimitSpinner = findViewById(R.id.limit_spinner);
        initializeSpinners();
        searchLiveButton = findViewById(R.id.search_live_button);
        searchOfflineButton = findViewById(R.id.search_offline_button);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemClickListener(onItemClickListener);

        //INITIALIZE VIEWMODEL
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(SearchViewModel.class);

        searchLiveButton.setOnClickListener((View view) -> {
            userSearchQuery = searchEditText.getText().toString();
            userSearchType = searchTypeSpinner.getSelectedItem().toString();
            userSearchLimit = searchLimitSpinner.getSelectedItem().toString();

            viewModel.init(userSearchQuery, userSearchType, userSearchLimit);
            viewModel.getSearchResultEntityLiveData()
                    .observe(MainActivity.this, searchResultEntities -> searchResultAdapter.setItems(searchResultEntities));


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
