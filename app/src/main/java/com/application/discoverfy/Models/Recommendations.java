package com.application.discoverfy.Models;

public class Recommendations {

    private String id;
    private String name;
    private String artists;

    // get id
    public String getId() {
        return id;
    }

    // set id
    public void setId(String id) {
        this.id = id;
    }

    // get name
    public String getName() {
        return name;
    }

    // set name
    public void setName(String name) {
        this.name = name;
    }

    // get artists
    public String getArtists() {
        return artists;
    }

    // set artists
    public void setArtists(String artists) {
        this.artists = artists;
    }
}
