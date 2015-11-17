package symph.teampuraprototype.fragments;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import symph.teampuraprototype.R;
import symph.teampuraprototype.activities.ContentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;

    private static final int RC_SIGN_IN = 0;

    private static final String SAVED_PROGRESS = "sign_in_progress";

    private GoogleApiClient mGoogleApiClient;

    private int mSignInProgress;

    private PendingIntent mSignInIntent;

    private int mSignInError;

    EditText mEmail;
    EditText mPassword;

    private boolean mIntentInProgress;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mEmail = (EditText) v.findViewById(R.id.et_email);
        mPassword = (EditText) v.findViewById(R.id.et_password);
        Button login = (Button) v.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ContentActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        SignInButton googlePlusLogin = (SignInButton) v.findViewById(R.id.google_sign_in_button);
        googlePlusLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                    mSignInProgress = STATE_SIGN_IN;
                }
            }
        });
        return v;
    }


    @Override
    public void onConnected(Bundle bundle) {
        mSignInProgress = STATE_DEFAULT;
        Toast.makeText(getActivity(), "Signed in.", Toast.LENGTH_SHORT).show();
        Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        mEmail.setText(currentUser.getName().toString());
        Intent i = new Intent(getActivity(), ContentActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RC_SIGN_IN) {
//            mIntentInProgress = false;
//
//            if (!mGoogleApiClient.isConnecting()) {
//                mGoogleApiClient.connect();
//            }
//        }
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == Activity.RESULT_OK) {
                    mSignInProgress = STATE_SIGN_IN;
                } else {
                    mSignInProgress = STATE_DEFAULT;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
//        if (!mIntentInProgress && connectionResult.hasResolution()) {
//            try {
//                mIntentInProgress = true;
//                getActivity().startIntentSenderForResult(connectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
//            } catch (IntentSender.SendIntentException e) {
//                mIntentInProgress = false;
//                mGoogleApiClient.connect();
//            }
//        }
        Log.i("LoginFragment", "onConnectionFailed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());

        if (connectionResult.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {
            Log.i("LoginFragment", "API Unavailable.");
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
                getActivity().startIntentSenderForResult(mSignInIntent.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i("LoginFragment", "Sign in intent could not be sent: " + e.getLocalizedMessage());
                mSignInProgress = STATE_SIGN_IN;
                mGoogleApiClient.connect();
            }
        }
    }
}
