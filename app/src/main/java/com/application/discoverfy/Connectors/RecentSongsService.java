package com.application.discoverfy.Connectors;

import com.application.discoverfy.Models.RecentSongs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecentSongsService {

    // process songs method
    public List<RecentSongs> processSongs(JSONObject response) {
        // list for songs
        List<RecentSongs> recentSongs = new ArrayList<RecentSongs>();

        try {
            // get items array
            JSONArray jsonArray = response.optJSONArray("items");
            for (int n = 0; n < jsonArray.length(); n++) {
                // get items object
                JSONObject itemObject = jsonArray.getJSONObject(n);
                // get track object
                JSONObject trackObject = itemObject.optJSONObject("track");
                // create new RecentSongs object and set id and name
                RecentSongs songs = new RecentSongs();
                songs.setId(trackObject.getString("id"));
                songs.setName(trackObject.getString("name"));

                // get album object
                JSONObject albumObject = trackObject.optJSONObject("album");
                // get artists array
                JSONArray artistsArray = albumObject.optJSONArray("artists");

                // build artists String
                StringBuilder artists = new StringBuilder();
                    for (int k = 0; k < artistsArray.length(); k++) {
                        // get artists objects from the array
                        JSONObject artistObject = artistsArray.getJSONObject(k);
                        // set artists
                        if (k < artistsArray.length()-1) {
                            artists.append(artistObject.getString("name")).append(", ");
                        }
                        else {
                            artists.append(artistObject.getString("name"));
                        }
                        String artistsString = artists.toString();
                        songs.setArtists(artistsString);
                    }
                    recentSongs.add(songs);
            }
            } catch (JSONException e) {
            e.printStackTrace();
        }
        return recentSongs;
    }
}

