package com.application.discoverfy.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Top_Tracks")
public class TopTracks {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "song_id")
    private String topId;
    @ColumnInfo(name = "song_name")
    private String topName;
    @ColumnInfo(name = "artists")
    private String topArtists;

    // get the song id
    public String getTopId() {
        return topId;
    }

    // set the song id
    public void setTopId(String topId) {
        this.topId = topId;
    }

    // get the song name
    public String getTopName() {
        return topName;
    }

    // set the song name
    public void setTopName(String topName) {
        this.topName = topName;
    }

    // get the artists
    public String getTopArtists() {
        return topArtists;
    }

    // set the artists
    public void setTopArtists(String topArtists) {
        this.topArtists = topArtists;
    }
}
