package com.critics.taste.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Similar {

    @SerializedName("Info")
    @Expose
    private List<Info> info = null;
    @SerializedName("Results")
    @Expose
    private List<Result> results = null;

    /**
     * No args constructor for use in serialization
     */
    public Similar() {
    }

    /**
     * @param results
     * @param info
     */
    public Similar(List<Info> info, List<Result> results) {
        super();
        this.info = info;
        this.results = results;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
