package com.application.discoverfy.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recently_Played")
public class RecentSongs {
    @NonNull
    //(autoGenerate = true)
    //private int uid;
    @PrimaryKey
    private String id;

    private String name;

    private String artists;

    public RecentSongs() {
        id = null;
    }

    // get the database id
   /*public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    */
    // get the song id
    public String getId() {
        return id;
    }

    // set the song id
    public void setId(String id) {
        this.id = id;
    }

    // get the song name
    public String getName() {
        return name;
    }

    // set the song name
    public void setName(String name) {
        this.name = name;
    }

    // get the songs artists
    public String getArtists() { return artists;};

    // set the songs artists
    public void setArtists(String artists) { this.artists = artists; }

    @Override
    public String toString() {
        return "RecentSongs{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artists='" + artists + '\'' +
                '}';
    }
}
