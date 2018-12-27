package com.rtodo.android.strt4.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.rtodo.android.strt4.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ProgressDialog mDialog;
    String txtEmail;
    String firstName;
    String txtId;
    String lastName;
    URL profilePictureURL;
    ImageView SignUpButton;
    CheckBox checkBox;
    TextView termsText;
    TextView termsText1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        SignUpButton = findViewById(R.id.SignUpID);
        checkBox = findViewById(R.id.checkBox);
        termsText = findViewById(R.id.Terms2);
        termsText1 = findViewById(R.id.Terms1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Regular.otf");
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
        termsText1.setTypeface(custom_font);
        termsText.setTypeface(bold_font);
        final Drawable drawable = getDrawable(R.drawable.ic_box_checked_true);
        final Drawable drawableOff = getDrawable(R.drawable.ic_checked_box);




        if (AccessToken.getCurrentAccessToken()!=null){
            Log.d("progress", "progress");
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.d("response: ", response.toString());
                    try{
                        //profilePictureURL = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");
                        //Log.d("response", profilePictureURL.toString());
                        firstName = object.getString("first_name");
                        lastName = object.getString("last_name");
                        txtId = object.getString("id");
                        txtEmail = object.getString("email");
                    } //catch (MalformedURLException e) {
                        //e.printStackTrace();
                    //}
                     catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("email", txtEmail);
                    intent.putExtra("id", txtId);
                    //intent.putExtra("avatar", profilePictureURL.toString());
                    startActivity(intent);
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,email,last_name,first_name");
            request.setParameters(parameters);
            request.executeAsync();
        }


        

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    SignUpButton.setImageResource(R.drawable.ic_group_21);
                    checkBox.setBackground(drawable);

                }else {
                    SignUpButton.setImageResource(R.drawable.ic_facebook_signup1);
                    checkBox.setBackground(drawableOff);

                }
            }
        });










        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox.isChecked()){
                    Toast.makeText(LoginActivity.this, "Accept the terms and conditions! ", Toast.LENGTH_LONG).show();
                }
                else{
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        mDialog = new ProgressDialog(LoginActivity.this);
                        mDialog.setMessage("Retrieving data...");
                        mDialog.show();

                        String accesstoken = loginResult.getAccessToken().getToken();

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                mDialog.dismiss();
                                Log.d("response: ", response.toString());
                                try{
                                    profilePictureURL = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");
                                    Log.d("response", profilePictureURL.toString());
                                    firstName = object.getString("first_name");
                                    lastName = object.getString("last_name");
                                    txtId = object.getString("id");
                                    txtEmail = object.getString("email");
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(LoginActivity.this, OnboardingActivity.class);
                                intent.putExtra("firstName", firstName);
                                intent.putExtra("lastName", lastName);
                                intent.putExtra("email", txtEmail);
                                intent.putExtra("id", txtId);
                                intent.putExtra("avatar", profilePictureURL.toString());
                                startActivity(intent);

                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,email,last_name,first_name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(LoginActivity.this, "Try again!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }});


        termsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });




    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.rtodo.android.strt4", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                Log.d("KeyHash", "in");
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("KeyHash", "error1");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Log.d("KeyHash", "error2");
            e.printStackTrace();
        }
    }
}
