package com.gbreed.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playSound(View view)
    {
        MediaPlayer transMedia = MediaPlayer.create(this, getResources().getIdentifier(view.getTag().toString(), "raw", getPackageName()));

        transMedia.start();
    }
}
