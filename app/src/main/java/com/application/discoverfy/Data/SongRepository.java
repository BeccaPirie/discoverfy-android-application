package com.application.discoverfy.Data;

import android.content.Context;

import com.application.discoverfy.Models.RecentSongs;

import java.util.List;

public class SongRepository {

    // instance
    public static SongRepository INSTANCE;

    // context
    private Context context;

    // RecentSongDao
    private RecentSongsDao recentSongsDao;

    // get SongRepository
    public static SongRepository getRepository(Context context) {
        if (INSTANCE == null) {
            synchronized (SongRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new SongRepository();
                    INSTANCE.context = context;
                    SongDatabase database = SongDatabase.getDatabase(context);
                    INSTANCE.recentSongsDao = database.songsDao();
            }
        }
        return INSTANCE;
    }

    // insert recently played songs into database
    public void storeRecentSongs(List<RecentSongs> recentSongs) {
        recentSongsDao.insertRecentSongs(recentSongs.toArray(new RecentSongs[recentSongs.size()]));
    }

    // update recently played songs in database
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



}
