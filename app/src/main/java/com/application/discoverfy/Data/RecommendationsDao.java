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

    // select the latest 20 entries
    @Query("SELECT * from Recommendations ORDER BY uid DESC LIMIT 20")
    public List<Recommendations> getAllRecommendations();
}
