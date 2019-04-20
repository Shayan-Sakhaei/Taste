package com.critics.taste.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("wTeaser")
    @Expose
    private String wTeaser;
    @SerializedName("wUrl")
    @Expose
    private String wUrl;
    @SerializedName("yUrl")
    @Expose
    private String yUrl;
    @SerializedName("yID")
    @Expose
    private String yID;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param yUrl
     * @param wUrl
     * @param yID
     * @param wTeaser
     * @param name
     * @param type
     */
    public Result(String name, String type, String wTeaser, String wUrl, String yUrl, String yID) {
        super();
        this.name = name;
        this.type = type;
        this.wTeaser = wTeaser;
        this.wUrl = wUrl;
        this.yUrl = yUrl;
        this.yID = yID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWTeaser() {
        return wTeaser;
    }

    public void setWTeaser(String wTeaser) {
        this.wTeaser = wTeaser;
    }

    public String getWUrl() {
        return wUrl;
    }

    public void setWUrl(String wUrl) {
        this.wUrl = wUrl;
    }

    public String getYUrl() {
        return yUrl;
    }

    public void setYUrl(String yUrl) {
        this.yUrl = yUrl;
    }

    public String getYID() {
        return yID;
    }

    public void setYID(String yID) {
        this.yID = yID;
    }
}
