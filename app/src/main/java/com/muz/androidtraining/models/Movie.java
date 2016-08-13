package com.muz.androidtraining.models;

/**
 * Created by Muz on 2016-08-14.
 */
public class Movie {
    public String title;
    public String description;
    public int imageId;

    Movie(String title, String description, int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }
}
