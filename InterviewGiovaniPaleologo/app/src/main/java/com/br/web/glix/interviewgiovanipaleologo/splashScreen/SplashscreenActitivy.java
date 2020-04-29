package com.br.web.glix.interviewgiovanipaleologo.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.br.web.glix.interviewgiovanipaleologo.R;
import com.br.web.glix.interviewgiovanipaleologo.loginScreen.LoginScreenActivity;

public class SplashscreenActitivy extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private Thread mSplashThread;
    private boolean mTouchClick = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        LinearLayout linearLayout = findViewById(R.id.llSplashScreen);

        if (linearLayout != null) {
            linearLayout.clearAnimation();
            linearLayout.startAnimation(anim);
        }

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        ImageView iv = findViewById(R.id.ivSplashScreen);

        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(SPLASH_TIME_OUT);
                        mTouchClick = true;
                    }
                } catch (InterruptedException ex) {
                }
                if (mTouchClick) {
                    Intent intent = new Intent(SplashscreenActitivy.this, LoginScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                    SplashscreenActitivy.this.finish();
                }
            }
        };
        mSplashThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSplashThread.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mTouchClick = true;
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
