package com.application.discoverfy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    // shared preferences for settings
    private SharedPreferences sharedPreferences2;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // shared preferences
        sharedPreferences2 = getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);

        // current settings
        String currentImageView = sharedPreferences2.getString(getString(R.string.prefer_image_choice), getString(R.string.shared_pref_image_default));

        // check the correct radio button
        if (currentImageView.equals(getString(R.string.shared_pref_image_default))) {
            RadioButton radioButton = findViewById(R.id.rb_show_image);
            radioButton.setChecked(true);
        } else {
            RadioButton radioButton = findViewById(R.id.rb_no_image);
            radioButton.setChecked(true);
        }
    }

    // on pause method
    @Override
    protected void onPause() {
        super.onPause();
        // get radio group
        RadioGroup radioGroup = findViewById(R.id.rg_image);
        // String to hold the visibility choice
        String imageChoice = "";
        // set the choice String depending on what radio button is selected
        if(radioGroup.getCheckedRadioButtonId() == (R.id.rb_show_image)) {
        imageChoice = getString(R.string.shared_pref_image_default);
        }
        else if(radioGroup.getCheckedRadioButtonId() == (R.id.rb_no_image)) {
            imageChoice = getString(R.string.shared_pref_image_null);
        }

        // apply the users choice to shared preferences
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString(getString(R.string.prefer_image_choice), imageChoice);
        editor.apply();
    }
}