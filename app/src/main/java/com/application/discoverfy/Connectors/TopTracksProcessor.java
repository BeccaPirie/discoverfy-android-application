package com.application.discoverfy.Connectors;

import com.application.discoverfy.Models.TopTracks;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopTracksProcessor {

    public List<TopTracks> processTopTracks(JSONObject response) {

        List<TopTracks> topTracks = new ArrayList<TopTracks>();

        try {
            // items array
            JSONArray itemsArray = response.optJSONArray("items");
            for(int i = 0, j = itemsArray.length(); i<j; i++) {
                // items
                JSONObject itemsObject = itemsArray.optJSONObject(i);
                TopTracks top = new TopTracks();
                // get song id
                top.setTopId(itemsObject.getString("id"));
                // get song title
                top.setTopName(itemsObject.getString("name"));
                // get album object
                JSONObject albumObject = itemsObject.optJSONObject("album");
                // get artists array
                JSONArray artistsArray = albumObject.optJSONArray("artists");

                // build top tracks String
                StringBuilder topArtists = new StringBuilder();
                for(int k = 0, n = artistsArray.length(); k<n; k++) {
                    // get artists object
                    JSONObject artistsObject = artistsArray.optJSONObject(k);
                    if(k < (n-1)) {
                        topArtists.append(artistsObject.getString("name")).append(", ") ;
                    } else {
                        topArtists.append(artistsObject.getString("name"));
                    }
                    String topArtistsString = topArtists.toString();
                    // set artists string
                    top.setTopArtists(topArtistsString);
                }
                // add data to the list
                topTracks.add(top);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topTracks;
    }
}
