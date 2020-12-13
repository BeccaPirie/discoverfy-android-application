package com.application.discoverfy.Connectors;

import com.application.discoverfy.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserService {

    // process user method
    public User processUser(JSONObject response) {
        User user = null;

        try {
            // create new User
            user = new User();
            // set the user id
            user.setId(response.getString("id"));
            // set the user display name
            user.setDisplayName(response.getString("display_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}