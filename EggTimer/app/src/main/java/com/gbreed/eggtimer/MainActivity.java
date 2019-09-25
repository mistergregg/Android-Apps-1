package com.gbreed.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    boolean timerRunning = false;
    CountDownTimer countDown;
    MediaPlayer mediaPlayer;
    SeekBar seekTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.airhorn);

        seekTime = findViewById(R.id.seekBarTime);
        final TextView timeText = findViewById(R.id.textTime);

        int startProgress = 30;
        int maxProgress = 300;

        seekTime.setMax(maxProgress);
        seekTime.setProgress(startProgress);

        timeText.setText(calcTime(startProgress));

        seekTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                timeText.setText(calcTime(progress));

                Log.i("progress", "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    public void startTimer(View view)
    {
        final TextView timeText = findViewById(R.id.textTime);
        final Button theButton = findViewById(R.id.buttonStart);
        Log.i("Start time1", "" + seekTime.getProgress());

        if(!timerRunning)
        {
            timerRunning = true;

            seekTime.setEnabled(false);

            theButton.setText("STOP!");

            countDown = new CountDownTimer(seekTime.getProgress() * 1000, 1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    double timeLeft = millisUntilFinished / 1000;

                    timeText.setText(calcTime((int)timeLeft));

                    Log.i("Time Left", "" + timeLeft);
                }

                @Override
                public void onFinish()
                {
                    mediaPlayer.start();
                    seekTime.setProgress(30);
                    timeText.setText(calcTime(30));
                    seekTime.setEnabled(true);
                    timerRunning = false;
                    theButton.setText("GO!");
                }
            }.start();
        }else
        {
            countDown.cancel();
            seekTime.setEnabled(true);
            seekTime.setProgress(30);
            timeText.setText(calcTime(seekTime.getProgress()));
            timerRunning = false;
            theButton.setText("GO!");
        }
    }

    public String calcTime(int mills)
    {
        if(mills < 60)
        {
            if(mills < 10)
                return "0:0" + Integer.toString(mills);

            return "0:" + Integer.toString(mills);
        }

        int minutes = mills / 60;
        int seconds = mills - (minutes * 60);

        if(seconds < 10)
            return Integer.toString(minutes) + ":0" + Integer.toString(seconds);

        return Integer.toString(minutes) + ":" + Integer.toString(seconds);
    }
}
