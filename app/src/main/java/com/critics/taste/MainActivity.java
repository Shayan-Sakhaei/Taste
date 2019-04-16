package com.critics.taste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.critics.taste.MainActivityFeature.DaggerMainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityModule;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.application.TasteApplication;
import com.critics.taste.database.entity.Result;
import com.critics.taste.database.entity.Similar;
import com.critics.taste.interfaces.TasteDiveWebservice;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    TasteDiveWebservice tasteDiveWebservice;

    @Inject
    SearchResultAdapter searchResultAdapter;

    EditText searchEditText;
    Spinner searchTypeSpinner;
    Spinner searchLimitSpinner;
    Button searchButton;
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
                .mainActivityModule(new MainActivityModule(this))
                .appComponent(TasteApplication.get(this).getAppComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);

        //INITIALIZE VIEWS
        searchEditText = findViewById(R.id.search_editText);
        searchTypeSpinner = findViewById(R.id.type_spinner);
        searchLimitSpinner = findViewById(R.id.limit_spinner);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        initializeSpinners();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSearchQuery = searchEditText.getText().toString();
                userSearchType = searchTypeSpinner.getSelectedItem().toString();
                userSearchLimit = searchLimitSpinner.getSelectedItem().toString();

                Call<Similar> call = tasteDiveWebservice.getSimilar(
                        "333837-TasteMyT-HBUN5GWG", "muse",
                        userSearchType, userSearchLimit);
                call.enqueue(new Callback<Similar>() {
                    @Override
                    public void onResponse(Call<Similar> call, Response<Similar> response) {
                        Similar similar = response.body();
                        List<Result> resultList = similar.getResults();
                        searchResultAdapter.setItems(resultList);
                        recyclerView.setAdapter(searchResultAdapter);
                    }

                    @Override
                    public void onFailure(Call<Similar> call, Throwable t) {

                    }
                });
            }
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
