package com.application.discoverfy.Connectors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Models.Recommendations;
import com.application.discoverfy.VolleyCallBack;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.SPOTIFY;

public class RecommendationsService {

    private static final String ENDPOINT = "https://api.spotify.com/v1/recommendations";
    private ArrayList<Recommendations> recommendations = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private RequestQueue requestQueue;

    public RecommendationsService(Context context) {
        sharedPreferences = context.getSharedPreferences(SPOTIFY, 0);
        requestQueue = Volley.newRequestQueue(context);
    }

    public ArrayList<Recommendations> getRecommendations() {
        return recommendations;
    }

    public ArrayList<Recommendations> getRecommendedSongs(final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            Gson gson = new Gson();
            JSONArray jsonArray = response.optJSONArray("tracks");
            for (int n = 0; n < jsonArray.length(); n++) {
                try {
                    JSONObject object = jsonArray.getJSONObject(n);
                    object = object.optJSONObject("track");
                    Recommendations recommendation = gson.fromJson(object.toString(), Recommendations.class);
                    recommendations.add(recommendation);
                    Log.d("TEST", String.valueOf(recommendations));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            callBack.onSuccess();
        }, error -> {
            Log.d("TEST", "error");
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
        return recommendations;
    }
}
