package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Connectors.UserService;
import com.application.discoverfy.Models.User;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // tag
    private static final String tag = "LoginActivity";

    // editor
    private SharedPreferences.Editor editor;
    // shared preferences
    private SharedPreferences sharedPreferences;

    // variables required for API
    private static final String CLIENT_ID = "842e1e18c0c14f29b0c1f6b2f3160497";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "com.example.discoverfy://callback";
    private static final String SCOPES = "user-read-recently-played, user-read-email, user-read-private";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    // onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_login);

        // shared preferences
        sharedPreferences = this.getSharedPreferences(getString(R.string.spotify), 0);

        // login button
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(this);

        // settings button
        Button settings = findViewById(R.id.btn_settings);
        settings.setOnClickListener(this);

        // image settings method
        imageVisibilitySettings();
    }

    // set visibility of image
    private void imageVisibilitySettings() {
        // image
        ImageView image = findViewById(R.id.iv_legend);
        // set the image visibility depending on what is stored in shared preferences
        SharedPreferences sharedPreferences2 = getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);
        String preference = sharedPreferences2.getString(getString(R.string.prefer_image_choice), getString(R.string.shared_pref_image_default));
        if(preference.equals(getString(R.string.shared_pref_image_default))) {
            image.setVisibility(View.VISIBLE);
        } else if(preference.equals(getString(R.string.shared_pref_image_null))) {
            image.setVisibility(View.INVISIBLE);
        }
    }

    // get the users id
    private void downloadUserProfile() {
        // endpoint
        String ENDPOINT = "https://api.spotify.com/v1/me";

        // build volley request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            UserService service = new UserService();
            User user = service.processUser(response);
            Log.d("TEST", user.id);

            // save the user id in shared preferences
            editor = getSharedPreferences(getString(R.string.spotify), 0).edit();
            editor.putString("user_id", user.id);
            editor.commit();

            // close the Activity and open RecentlyPlayedActivity
            Intent intent1 = new Intent(LoginActivity.this,
                    RecentlyPlayedActivity.class);
            startActivity(intent1);
        }, error -> {
            Toast.makeText(getApplicationContext(), getString(R.string.user_error), Toast.LENGTH_LONG).show();
            Log.d(tag, error.getLocalizedMessage());
        }) {
            // get headers method
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // set bearer token as a header
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString(AUTH_TOKEN, "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        // build queue and make the request
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
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
                    editor = getSharedPreferences(getString(R.string.spotify), 0).edit();
                    editor.putString(AUTH_TOKEN, response.getAccessToken());
                    editor.apply();
                    downloadUserProfile();
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

    // on resume
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
        imageVisibilitySettings();
    }

    // on start
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "is in onStart");
    }

    // on stop
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "is in onStop");
    }

    // on destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "is in onDestroy");
    }

    // on pause
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "is in onPause");
    }


}
