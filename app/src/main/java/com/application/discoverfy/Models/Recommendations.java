package com.application.discoverfy.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recommendations")
public class Recommendations {
    @NonNull
    @PrimaryKey
    private String id;

    private String name;

    private String artists;

    public Recommendations() {
        id = null;
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

    @Override
    public String toString() {
        return "Recommendations{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artists='" + artists + '\'' +
                '}';
    }
}
