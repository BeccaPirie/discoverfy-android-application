package com.application.discoverfy.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.application.discoverfy.Models.RecentSongs;

@Database(entities = {RecentSongs.class}, version = 1)
public abstract class SongDatabase extends RoomDatabase {
    // dao
    public abstract RecentSongsDao songsDao();
    // instance
    private static SongDatabase INSTANCE;

    // get the songDatabase
    public static SongDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SongDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            SongDatabase.class, "recent_songs_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        } return INSTANCE;
    }
}
