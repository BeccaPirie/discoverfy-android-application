package com.application.discoverfy.Models;

public class RecentSongs {

    private String id;
    private String name;
    private String artists;

    public RecentSongs(String id, String name, String artists) {
        this.id = id;
        this.name = name;
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.id = name;
    }

    public String getArtists() { return artists;};

    public void setArtists(String artists) { this.artists = artists; }
}
