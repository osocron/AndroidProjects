package com.example.osocron.alpha_rest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.osocron.alpha_rest.R;
import com.example.osocron.alpha_rest.model.OAuthResponse;

public class ProveedoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);
        Bundle bundle = getIntent().getExtras();
        OAuthResponse oAuthResponse = bundle.getParcelable("com.example.osocron.alpha_rest.model.OAuthResponse");
    }

}
