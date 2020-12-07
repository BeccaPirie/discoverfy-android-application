package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.application.discoverfy.LoginActivity.SPOTIFY;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_USER_INPUT = "com.application.discoverfy.USER_INPUT";

    public String getInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // search button
        Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(this);

        // settings button
        Button settings = findViewById(R.id.btn_settings2);
        settings.setOnClickListener(this);

        // display the users Spotify name
        TextView displayUser = findViewById(R.id.textView);

        // get the user id from Shared Preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        displayUser.setText(sharedPreferences.getString("user_id", "no user"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search) {

            // get the user input and convert input to a String
            EditText userInput = findViewById(R.id.et_search_artist);
            getInput = String.valueOf(userInput.getText());

            // open the results activity
            Intent search = new Intent(MainActivity.this, ResultsActivity.class);
            search.putExtra(EXTRA_USER_INPUT, getInput);
            startActivity(search);


        } if (v.getId() == R.id.btn_settings2) {
            // open the setting activity
            Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settings);
        }
    }
}