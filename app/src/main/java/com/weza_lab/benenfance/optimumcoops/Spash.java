package com.weza_lab.benenfance.optimumcoops;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Spash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    public void onAtachedToWindows() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        startAnimations();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Spash.this, LoginActivity2.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(anim);

        Animation anim_image = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim_image.reset();
        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.clearAnimation();
        imageView.startAnimation(anim_image);
    }
}
