package com.application.discoverfy.Connectors;

import com.application.discoverfy.Models.Recommendations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsService {

    // process song recommendations method
    public List<Recommendations> processRecommendations(JSONObject response) {
        // list for recommendations
        List<Recommendations> recommendations = new ArrayList<Recommendations>();

        try {
        // get tracks array
        JSONArray tracksArray = response.optJSONArray("tracks");
        for (int i = 0; i<tracksArray.length(); i++) {
                // get track objects from the array
                JSONObject trackObject = tracksArray.getJSONObject(i);
                // create new Recommendations object and set id and name
                Recommendations recommend = new Recommendations();
                recommend.setId(trackObject.getString("id"));
                recommend.setName(trackObject.getString("name"));

                // get artists array
                JSONArray artistsArray = trackObject.getJSONArray("artists");

                // build artists string
                StringBuilder artists = new StringBuilder();
                for (int k = 0, n = artistsArray.length(); k<n; k++) {
                    // get artist objects from the array
                    JSONObject artistObject = artistsArray.getJSONObject(k);

                    if (k < (n-1)) {
                        artists.append(artistObject.getString("name")).append(", ");
                    } else {
                        artists.append(artistObject.getString("name"));
                    }
                    String artistString = artists.toString();
                    // set artists
                    recommend.setArtists(artistString);
                }
                recommendations.add(recommend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}
