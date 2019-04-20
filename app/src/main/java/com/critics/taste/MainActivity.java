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
import android.widget.Switch;

import com.critics.taste.MainActivityFeature.DaggerMainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityComponent;
import com.critics.taste.MainActivityFeature.MainActivityModule;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.application.TasteApplication;
import com.critics.taste.database.entity.Api;
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
    Switch searchInfoSwitch;
    Button searchButton;
    RecyclerView recyclerView;

    String userSearchQuery;
    String userSearchType;
    String userSearchLimit;
    boolean switchState;

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
        initializeSpinners();
        searchInfoSwitch = findViewById(R.id.info_switch);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchResultAdapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSearchQuery = searchEditText.getText().toString();
                userSearchType = searchTypeSpinner.getSelectedItem().toString();
                userSearchLimit = searchLimitSpinner.getSelectedItem().toString();
                switchState = searchInfoSwitch.isChecked();

                Call<Api> call;

                if (switchState) {
                    call = tasteDiveWebservice.getApiByTypeAndInfo(
                            "333837-TasteMyT-HBUN5GWG", userSearchQuery,
                            userSearchType, userSearchLimit);
                } else if (userSearchType != "mixed") {
                    call = tasteDiveWebservice.getApiByType(
                            "333837-TasteMyT-HBUN5GWG", userSearchQuery,
                            userSearchType, userSearchLimit);
                } else {
                    call = tasteDiveWebservice.getApiMixed(
                            "333837-TasteMyT-HBUN5GWG", userSearchQuery,
                            userSearchLimit);
                }
                call.enqueue(new Callback<Api>() {
                    @Override
                    public void onResponse(Call<Api> call, Response<Api> response) {
                        if (response.isSuccessful()) {
                            Similar similar = response.body().getSimilar();
                            List<Result> results = similar.getResults();
                            searchResultAdapter.setItems(results);
                        }
                    }

                    @Override
                    public void onFailure(Call<Api> call, Throwable t) {

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
