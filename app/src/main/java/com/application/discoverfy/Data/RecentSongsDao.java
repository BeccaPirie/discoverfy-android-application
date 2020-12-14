package com.application.discoverfy.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.application.discoverfy.Models.RecentSongs;

import java.util.List;

@Dao
public interface RecentSongsDao {

    // insert songs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecentSongs(RecentSongs... recentSongs);

    // update songs
    @Update
    public void updateRecentSongs(RecentSongs... recentSongs);

    // delete songs
    @Delete
    public void deleteRecentSongs(RecentSongs... recentSongs);

    // select 20 entries
    @Query("SELECT * from Recently_Played LIMIT 20")
    public List<RecentSongs> getAllSongs();

}

