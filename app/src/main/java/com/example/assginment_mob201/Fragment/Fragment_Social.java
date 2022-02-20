package com.example.assginment_mob201.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assginment_mob201.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Fragment_Social extends Fragment {
    private Button btnFacebookLogin;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        this.initViewById(view);

        this.printHashKey();

        this.initFacebookSdk();

        this.initFacebookLogin();

        this.initButtonClick();

        return view;
    }

    private void initButtonClick() {
        this.btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManager.logInWithReadPermissions(Fragment_Social.this
                        , Arrays.asList("email", "public_profile", "user_birthday"));
                ;
            }
        });
    }

    private void initFacebookLogin() {
        this.loginManager = LoginManager.getInstance();
        this.callbackManager = CallbackManager.Factory.create();
        this.loginManager.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                        if (jsonObject != null) {
                            try {
                                String name = jsonObject.getString("name");
                                String email = jsonObject.getString("email");
                                String fbUserID = jsonObject.getString("id");
                                Toast.makeText(getActivity(), "Đặng nhập thành công", Toast.LENGTH_LONG).show();
                                disConnectFromFacebook();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id, name, email, gender, birthday");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Hủy bỏ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.v("LoginScreen", "----onError: " + e.getMessage());
            }
        });
    }

    private void disConnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return;
        }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(@NonNull GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }

    private void initFacebookSdk() {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        this.callbackManager = CallbackManager.Factory.create();
    }

    private void initViewById(View view) {
        this.btnFacebookLogin = view.findViewById(R.id.button_facebook);
    }

    private void printHashKey() {
        try {

            PackageInfo info
                    = getActivity().getPackageManager().getPackageInfo(
                    "com.android.facebookloginsample",
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md
                        = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        Base64.encodeToString(
                                md.digest(),
                                Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}