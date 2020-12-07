package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Connectors.UserService;
import com.application.discoverfy.Models.User;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String tag = "Discoverfy";

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private SharedPreferences sharedPreferences2;

    private RequestQueue requestQueue;

    private static final String CLIENT_ID = "842e1e18c0c14f29b0c1f6b2f3160497";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "com.example.discoverfy://callback";
    private static final String SCOPES = "user-read-recently-played,user-library-modify,user-read-email,user-read-private";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    public static final String SPOTIFY = "SPOTIFY"; // put as string resource for best practice

    // onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_login);

        // shared preferences
        sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        requestQueue = Volley.newRequestQueue(this);

        // login button
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(this);

        // settings button
        Button settings = findViewById(R.id.btn_settings);
        settings.setOnClickListener(this);

        imageVisibilitySettings();
    }

    // set visibility of image
    private void imageVisibilitySettings() {
        // image
        ImageView image = findViewById(R.id.iv_legend);

        // set the image visibility depending on what is stored in shared preferences
        sharedPreferences2 = getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);
        String preference = sharedPreferences2.getString(getString(R.string.prefer_image_choice), getString(R.string.shared_pref_image_default));
        if(preference.equals(getString(R.string.shared_pref_image_default))) {
            image.setVisibility(View.VISIBLE);
        } else if(preference.equals(getString(R.string.shared_pref_image_null))) {
            image.setVisibility(View.INVISIBLE);
        }
    }

    // get the users information and save it in shared preferences
    private void waitForUserInfo() {
        UserService userService = new UserService(requestQueue, sharedPreferences);

        userService.get(() -> {
            // returns User
            User user = userService.getUser();

            // save the user id in shared preferences
            editor = getSharedPreferences(SPOTIFY, 0).edit();
            editor.putString("user_id", user.id);
            Log.d("TEST", "GOT USER INFORMATION " + user.id);
            editor.commit();

            // close the Activity and open RecentlyPlayedActivity
            Intent intent1 = new Intent(LoginActivity.this,
                    RecentlyPlayedActivity.class);
            //intent1.putExtra(AUTH_TOKEN, response.getAccessToken());
            startActivity(intent1);
        });
    }

    // send a request to Spotify's authentication service and receive an authentication token to make calls to the API
    private void loginSpotify() {
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        //builder.setShowDialog(true);
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    // check the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // if result is correct and from the right activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // response contains token and was successful
                case CODE:
                    break;
                case TOKEN:
                    // successful response
                    // save the authentication token in Shared Preferences
                    editor = getSharedPreferences(SPOTIFY, 0).edit();
                    editor.putString(AUTH_TOKEN, response.getAccessToken());
                    editor.apply();
                    Log.d("TEST", "access token " + response.getAccessToken());
                    waitForUserInfo();
                    //destroy();
                    break;

                // error returned
                case ERROR:
                    // error response
                    break;

                // cancelled
                case EMPTY:
                    break;
                case UNKNOWN:
                    break;
                default:
                    // other cases
            }
        }
    }

    // set onClickListeners to the buttons
    @Override
    public void onClick(View v) {
        // if the login button is pressed, run loginSpotify() method
        if(v.getId() == R.id.btn_login) {
            loginSpotify();
        }
        // if the settings button is clicked, open SettingsActivity
        else if(v.getId() == R.id.btn_settings) {
            Intent settings = new Intent(LoginActivity.this, SettingsActivity.class);
            startActivity(settings);
        }
    }

    /*
    // close activity
    public void destroy(){
        LoginActivity.this.finish();
    }

     */


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
        imageVisibilitySettings();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "is in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "is in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "is in onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "is in onPause");
    }


}
