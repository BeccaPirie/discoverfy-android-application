package com.application.discoverfy.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.application.discoverfy.Models.TopTracks;

import java.util.List;

@Dao
public interface TopTracksDao {

    // insert songs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTopTracks(TopTracks... topTracks);

    // update songs
    @Update
    public void updateTopTracks(TopTracks... topTracks);

    // delete songs
    @Delete
    public void deleteTopTracks(TopTracks... topTracks);

    // select 20 entries
    @Query("SELECT * from Top_Tracks LIMIT 20")
    public List<TopTracks> getAllTracks();
}
