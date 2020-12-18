package com.application.discoverfy.Data;

import android.content.Context;

import com.application.discoverfy.Models.RecentSongs;
import com.application.discoverfy.Models.Recommendations;
import com.application.discoverfy.Models.TopTracks;

import java.util.List;

public class SongRepository {

    // instance
    public static SongRepository INSTANCE;

    // context
    private Context context;

    // RecentSongDao
    private RecentSongsDao recentSongsDao;

    // TopTracksDao
    private TopTracksDao topTracksDao;

    // RecommendationsDao
    private RecommendationsDao recommendationsDao;

    // get SongRepository
    public static SongRepository getRepository(Context context) {
        if (INSTANCE == null) {
            synchronized (SongRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new SongRepository();
                    INSTANCE.context = context;
                    SongDatabase database = SongDatabase.getDatabase(context);
                    INSTANCE.recentSongsDao = database.songsDao();
                    INSTANCE.topTracksDao = database.topTracksDao();
                    INSTANCE.recommendationsDao = database.recommendationsDao();
            }
        }
        return INSTANCE;
    }

    // insert recently played songs into database
    public void storeRecentSongs(List<RecentSongs> recentSongs) {
        recentSongsDao.insertRecentSongs(recentSongs.toArray(new RecentSongs[recentSongs.size()]));
    }

    // update recently played songs in the database
    public void updateRecentSongs(List<RecentSongs> recentSongs) {
        recentSongsDao.updateRecentSongs(recentSongs.toArray(new RecentSongs[recentSongs.size()]));
    }

    // delete recently played songs from database
    public void deleteRecentSongs(List<RecentSongs> recentSongs) {
        recentSongsDao.deleteRecentSongs(recentSongs.toArray(new RecentSongs[recentSongs.size()]));
    }

    // get all recently played songs
    public List<RecentSongs> getAllSongs() {
        return recentSongsDao.getAllSongs();
    }

    // insert top tracks into database
    public void storeTopTracks(List<TopTracks> topTracks) {
        topTracksDao.insertTopTracks(topTracks.toArray(new TopTracks[topTracks.size()]));
    }

    // update top tracks in the database
    public void updateTopTracks(List<TopTracks> topTracks) {
        topTracksDao.updateTopTracks(topTracks.toArray(new TopTracks[topTracks.size()]));
    }

    // delete top tracks from the database
    public void deleteTopTracks(List<TopTracks> topTracks) {
        topTracksDao.deleteTopTracks(topTracks.toArray(new TopTracks[topTracks.size()]));
    }

    // get all top tracks
    public List<TopTracks> getAllTracks() {
        return topTracksDao.getAllTracks();
    }


    // insert recommendations into database
    public void storeRecommendations(List<Recommendations> recommendations) {
        recommendationsDao.storeRecommendations(recommendations.toArray(new Recommendations[recommendations.size()]));
    }

    // update recommendations in the database
    public void updateRecommendations(List<Recommendations> recommendations) {
        recommendationsDao.updateRecommendations(recommendations.toArray(new Recommendations[recommendations.size()]));
    }

    // delete recommendations from database
    public void deleteRecommendations(List<Recommendations> recommendations) {
        recommendationsDao.deleteRecommendations(recommendations.toArray(new Recommendations[recommendations.size()]));
    }

    // get all recommendations
    public List<Recommendations> getAllRecommendations() {
        return recommendationsDao.getAllRecommendations();
    }

}
