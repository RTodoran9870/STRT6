package com.rtodo.android.strt4.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rtodo.android.strt4.R;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        Bundle bundle = getIntent().getExtras();
        final String last_name = bundle.getString("lastName");
        final String first_name = bundle.getString("firstName");
        final String email = bundle.getString("email");
        final String id = bundle.getString("id");
        final String profilePictureURL = bundle.getString("avatar");
        Button finnishButton = findViewById(R.id.finnishID);
        Log.d("response ", last_name + first_name + email + id + profilePictureURL);

        finnishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnboardingActivity.this, CardGetterActivity.class);
                intent.putExtra("lastName", last_name);
                intent.putExtra("firstName", first_name);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("avatar", profilePictureURL);
                startActivity(intent);
            }
        });
    }
}
