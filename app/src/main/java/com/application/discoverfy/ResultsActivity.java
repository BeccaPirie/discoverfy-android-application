package com.application.discoverfy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.application.discoverfy.MainActivity.EXTRA_USER_INPUT;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent search = getIntent();
        String display = search.getStringExtra(EXTRA_USER_INPUT);

        // display the search input in the heading
        TextView userInput = findViewById(R.id.tv_user_input);
        userInput.setText(display);
    }
}