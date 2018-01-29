package com.ab2018.vizyondakilerdatabase;

import java.io.Serializable;

public class Movie implements Serializable {
    int id;
    String name;
    String overview;
    int imageCode;
    String originalLanguage;
    double voteAverage;


    protected Movie(int id, String name, String overview, int imageCode,String originalLanguage,double voteAverage)
    {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.imageCode = imageCode;
        this.originalLanguage = originalLanguage;
        this.voteAverage = voteAverage;


    }

    public int getImageCode() {
        return imageCode;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

}

