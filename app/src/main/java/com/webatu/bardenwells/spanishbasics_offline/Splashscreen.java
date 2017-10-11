package com.webatu.bardenwells.spanishbasics_offline;

/**
 * Created by srikanth on 11/10/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Splashscreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final TextView tv = (TextView) findViewById(R.id.textView);
        final ImageView iv = (ImageView) findViewById(R.id.splash);
        final Intent intent = new Intent(Splashscreen.this,
                MainActivity.class);
        iv.startAnimation(anim);
        tv.startAnimation(anim);


        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){

                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
            timer.start();

    }
}
