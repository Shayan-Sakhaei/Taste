package com.critics.taste.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Api {

    @SerializedName("Similar")
    @Expose
    private Similar similar;

    /**
     * No args constructor for use in serialization
     */
    public Api() {
    }

    /**
     * @param similar
     */
    public Api(Similar similar) {
        super();
        this.similar = similar;
    }

    public Similar getSimilar() {
        return similar;
    }

    public void setSimilar(Similar similar) {
        this.similar = similar;
    }
}
