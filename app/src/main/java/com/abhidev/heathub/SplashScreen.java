package com.abhidev.heathub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // initialize animations
        Animation bottom2 = AnimationUtils.loadAnimation(this, R.anim.bottom_animation2);
        Animation bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //setting bottom animation
        TextView textAppname = findViewById(R.id.appName);
        textAppname.setAnimation(bottom2);

        //setting bottom2 animation
        CardView cardPhoto = findViewById(R.id.splashCardview);
        cardPhoto.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }
        }, 3000);
    }
}