package com.rtodo.android.strt4.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.rtodo.android.strt4.R;

public class CardGetterActivity extends AppCompatActivity {

    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "63k7rggbft9d3fhj",
            "tp7kky24mz6vwnbm",
            "ea4a2f445ef2e78f54a7094d6cd6960d"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_getter);




    }
}
