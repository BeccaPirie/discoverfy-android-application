package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Connectors.UserProcessor;
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
    private static final String REDIRECT_URI = "com.application.discoverfy://callback";
    private static final String SCOPES = "user-read-recently-played, user-read-email, user-read-private, user-top-read, playlist-read-private";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    // onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_login);

        // shared preferences
        sharedPreferences = this.getSharedPreferences(getString(R.string.spotify), MODE_PRIVATE);

        // login button
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(this);
    }

    // get the users id
    private void downloadUserProfile() {
        // endpoint
        String ENDPOINT = "https://api.spotify.com/v1/me";

        // build volley request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            UserProcessor service = new UserProcessor();
            User user = service.processUser(response);

            // save the user display name in shared preferences
            editor = getSharedPreferences(getString(R.string.spotify), MODE_PRIVATE).edit();
            editor.putString("display_name", user.displayName);
            editor.commit();

            // close the Activity and open RecentlyPlayedActivity
            Intent intent1 = new Intent(LoginActivity.this,
                    MainActivity.class);
            startActivity(intent1);
        }, error -> {
            Toast.makeText(getApplicationContext(), getString(R.string.user_error), Toast.LENGTH_LONG).show();
            Log.d(tag, error.getLocalizedMessage());
        }) {
            // get headers method
            @Override
            public Map<String, String> getHeaders() {
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
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    // check the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // if result is correct
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                case TOKEN:
                    // successful response
                    // save the authentication token in Shared Preferences
                    editor = getSharedPreferences(getString(R.string.spotify), 0).edit();
                    editor.putString(AUTH_TOKEN, response.getAccessToken());
                    editor.apply();
                    downloadUserProfile();
                    break;
                // error returned
                case ERROR:
                    Toast.makeText(getApplicationContext(), getString(R.string.user_error), Toast.LENGTH_LONG).show();
                    break;
                // cancelled
                case EMPTY:
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
    }

    // on resume
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
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
