package symph.teampuraprototype.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import symph.teampuraprototype.R;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;

    public static final int RC_SIGN_IN = 0;
    public static final int RC_SIGN_OUT = 1;


    private GoogleApiClient mGoogleApiClient;

    private int mSignInProgress;

    private PendingIntent mSignInIntent;

    private int mSignInError;

    private Button mLoginButton;
    private Button mSignInButton;
    private Button mSignOutButton;
    TextView accessToken, userId;

    private CallbackManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        manager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ContentActivity.class);
                startActivity(i);
                finish();
            }
        });
//        userId = (TextView) findViewById(R.id.user_id);
//        accessToken = (TextView) findViewById(R.id.access_token);
//        LoginButton fbLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
//        fbLoginButton.registerCallback(manager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Intent i = new Intent(LoginActivity.this, ContentActivity.class);
//                startActivity(i);
//                finish();
////                accessToken.setText(loginResult.getAccessToken().getToken());
////                userId.setText(loginResult.getAccessToken().getUserId());
////                Toast.makeText(LoginActivity.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//
//            }
//        });
//        LoginManager.getInstance().registerCallback(manager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Toast.makeText(LoginActivity.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(LoginActivity.this, ContentActivity.class);
//                startActivity(i);
//                finish();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//
//            }
//        });
//        mLoginButton = (Button) findViewById(R.id.login_button);
//        mLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this, ContentActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//        mSignInButton = (Button) findViewById(R.id.google_sign_in_button);
//        mSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!mGoogleApiClient.isConnecting()) {
//                    mGoogleApiClient.connect();
//                    mSignInProgress = STATE_SIGN_IN;
//                }
//            }
//        });
//        mSignOutButton = (Button) findViewById(R.id.google_sign_out_button);
//        mSignOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSignedOut();
//            }
//        });
//
//        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

//        if (mGoogleApiClient.isConnected()) {
////            mGoogleApiClient.disconnect();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        manager.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    mSignInProgress = STATE_SIGN_IN;
                } else {
                    mSignInProgress = STATE_DEFAULT;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
            case RC_SIGN_OUT:
                if (resultCode == RESULT_OK) {
                    mSignInProgress = STATE_DEFAULT;
                    onSignedOut();
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
//        mSignInButton.setEnabled(false);
        mSignInProgress = STATE_DEFAULT;
        mSignInButton.setVisibility(View.GONE);
        mSignOutButton.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, ContentActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {
            Log.i("LoginActivity", "API Unavailable.");
        } else if (mSignInProgress != STATE_IN_PROGRESS) {
            mSignInIntent = connectionResult.getResolution();
            mSignInError = connectionResult.getErrorCode();

            if (mSignInProgress == STATE_SIGN_IN) {
                resolveSignInError();
            }
        }
    }

    private void resolveSignInError() {
        if (mSignInIntent != null) {
            try {
                mSignInProgress = STATE_IN_PROGRESS;
                startIntentSenderForResult(mSignInIntent.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i("LoginFragment", "Sign in intent could not be sent: " + e.getLocalizedMessage());
                mSignInProgress = STATE_SIGN_IN;
                mGoogleApiClient.connect();
            }
        }
    }

    private void onSignedOut() {
        if (!mGoogleApiClient.isConnecting()) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }
            mSignInButton.setVisibility(View.VISIBLE);
            mSignOutButton.setVisibility(View.GONE);
        }
    }
}
