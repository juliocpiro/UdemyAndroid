package com.jcsar.seccion_03_recycler_card_view.models;

/**
 * Created by NP000216 on 12/10/2017.
 */

public class Movie {
    private String name;
    private int poster;

    public Movie() {
    }

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
