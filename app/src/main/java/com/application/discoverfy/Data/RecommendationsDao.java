package com.application.discoverfy.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.application.discoverfy.Models.Recommendations;

import java.util.List;

@Dao
public interface RecommendationsDao {

    // insert recommendations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void storeRecommendations(Recommendations... recommendations);

    // update recommendations
    @Update
    public void updateRecommendations(Recommendations... recommendations);

    // delete recommendations
    @Delete
    public void deleteRecommendations(Recommendations... recommendations);

    // get all recommendations
    @Query("SELECT * from Recommendations ORDER BY name ASC")
    public List<Recommendations> getAllRecommendations();
}
