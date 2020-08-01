package com.spatalabz.waller.model.api;

import com.google.gson.annotations.SerializedName;

public class Photo {


    @SerializedName("id")
    private long id;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("url")
    private String url;
    @SerializedName("photographer")
    private String photographer;
    @SerializedName("photographer_url")
    private String photographer_url;
    @SerializedName("photographer_id")
    private long photographer_id;
    @SerializedName("src")
    private Src src;
    @SerializedName("liked")
    private Boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographer_url() {
        return photographer_url;
    }

    public void setPhotographer_url(String photographer_url) {
        this.photographer_url = photographer_url;
    }

    public long getPhotographer_id() {
        return photographer_id;
    }

    public void setPhotographer_id(long photographer_id) {
        this.photographer_id = photographer_id;
    }

    public Src getSrc() {
        return src;
    }

    public void setSrc(Src src) {
        this.src = src;
    }
}

