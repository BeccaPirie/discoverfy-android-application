package com.application.discoverfy.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recommendations")
public class Recommendations {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String id;

    private String name;

    private String artists;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

}
