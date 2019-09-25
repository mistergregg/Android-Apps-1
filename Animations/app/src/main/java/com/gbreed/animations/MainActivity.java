package com.gbreed.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bartImageView = findViewById(R.id.bartImageView);
        bartImageView.setX(-1000);
        bartImageView.animate().translationXBy(1000).rotation(1800).alpha(1).setDuration(2000);
    }

    public void fade(View view)
    {

        ImageView bartImageView = findViewById(R.id.bartImageView);
        ImageView homerImageView = findViewById(R.id.homerImageView);

        /*if(bartImageView.getAlpha() == 1)
        {
            bartImageView.animate().alpha(0).setDuration(2000);
            homerImageView.animate().alpha(1).setDuration(2000);
        }else if(bartImageView.getAlpha() == 0){
            homerImageView.animate().alpha(0).setDuration(2000);
            bartImageView.animate().alpha(1).setDuration(2000);

            .translationYBy(1000).rotation(1800)
        }*/


    }
}
