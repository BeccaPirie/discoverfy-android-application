package com.application.discoverfy.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recommendations")
public class Recommendations {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "song_id")
    private String id;

    private String name;

    private String artists;

    private String uri;

    private String webLink;

    // get the uid
    public int getUid() {
        return uid;
    }

    // set the uid
    public void setUid(int uid) {
        this.uid = uid;
    }

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

    // get the artists
    public String getArtists() {
        return artists;
    }

    // set the artists
    public void setArtists(String artists) {
        this.artists = artists;
    }

    // get the uri
    public String getUri() {
        return uri;
    }

    // set the uri
    public void setUri(String uri) {
        this.uri = uri;
    }

    // get the web link
    public String getWebLink() {
        return webLink;
    }

    // set the web link
    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

}
