package com.application.discoverfy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;


public class LoginActivity extends AppCompatActivity {

    private static final String tag = "Discoverfy";

    private static final String CLIENT_ID = "842e1e18c0c14f29b0c1f6b2f3160497";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "com.example.discoverfy://callback";
    private static final String SCOPES = "user-read-recently-played,user-library-modify,user-read-email,user-read-private";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_login);

        // if login button is pressed, allow the user to log in to Spotify
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(view -> {
            if(view.getId() == R.id.btn_login) {
                loginSpotify();
            }
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
                case TOKEN:
                    // successful response, close the Activity and open RecentlyPlayedActivity
                    Intent intent1 = new Intent(LoginActivity.this,
                            RecentlyPlayedActivity.class);
                    intent1.putExtra(AUTH_TOKEN, response.getAccessToken());
                    startActivity(intent1);
                    destroy();
                    break;

                // error returned
                case ERROR:
                    // error response
                    break;

                // cancelled
                default:
                    // other cases
            }
        }
    }

    // close activity
    public void destroy(){
        LoginActivity.this.finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
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
