package com.critics.taste.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.critics.taste.MainActivity;
import com.critics.taste.R;
import com.critics.taste.database.entity.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private final Picasso picasso;
    private final MainActivity mainActivity;
    private List<Result> resultList = new ArrayList<>();

    public SearchResultAdapter(MainActivity mainActivity, Picasso picasso) {
        this.picasso = picasso;
        this.mainActivity = mainActivity;
    }


    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_search_result,
                        parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.nameTextView.setText(result.getName());
        holder.typeTextView.setText(result.getType());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setItems(List<Result> searchResultList) {
        this.resultList = searchResultList;
        notifyDataSetChanged();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView typeTextView;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            typeTextView = itemView.findViewById(R.id.type);
        }
    }
}
