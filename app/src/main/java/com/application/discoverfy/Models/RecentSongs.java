package com.application.discoverfy.Models;

public class RecentSongs {

    // id, name and artists variables
    private String id;
    private String name;
    private String artists;

    // constructor class
    public RecentSongs(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
        this.id = name;
    }

    // get artists
    public String getArtists() { return artists;};

    // set artists
    public void setArtists(String artists) { this.artists = artists; }


}
