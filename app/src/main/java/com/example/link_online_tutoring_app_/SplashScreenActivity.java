package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate( Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.splashscreenlayout);


        final Button continueBtn = findViewById(R.id.ContinueButton);


    }

    public void onClickContinue(View view) {
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
