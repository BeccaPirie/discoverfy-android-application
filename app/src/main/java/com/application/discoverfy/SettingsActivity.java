package com.application.discoverfy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences2 = getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);

        String currentImageView = sharedPreferences2.getString(getString(R.string.prefer_image_choice), getString(R.string.shared_pref_image_default));

        if (currentImageView.equals(getString(R.string.shared_pref_image_default))) {
            RadioButton radioButton = findViewById(R.id.rb_show_image);
            radioButton.setChecked(true);
        } else {
            RadioButton radioButton = findViewById(R.id.rb_no_image);
            radioButton.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        RadioGroup radioGroup = findViewById(R.id.rg_image);
        String imageChoice = "";
        if(radioGroup.getCheckedRadioButtonId() == (R.id.rb_show_image)) {
        imageChoice = getString(R.string.shared_pref_image_default);
        }
        else if(radioGroup.getCheckedRadioButtonId() == (R.id.rb_no_image)) {
            imageChoice = getString(R.string.shared_pref_image_null);
        }

        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString(getString(R.string.prefer_image_choice), imageChoice);
        editor.apply();
    }
}