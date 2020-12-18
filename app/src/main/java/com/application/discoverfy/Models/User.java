package com.application.discoverfy.Models;

public class User {

    public String id;
    public String displayName;

    // get the user id
    public String getId() {
        return id;
    }

    // set the user id
    public void setId(String id) {
        this.id = id;
    }

    // get the user display name
    public String getDisplayName() {
        return displayName;
    }

    // set the user display name
    public void setDisplayName(String display_name) {
        this.displayName = display_name;
    }
}
