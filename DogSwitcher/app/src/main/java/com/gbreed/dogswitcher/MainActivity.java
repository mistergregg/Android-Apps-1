package com.gbreed.dogswitcher;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dogSwitch(View view)
    {
        ImageView dogs = findViewById(R.id.imageViewDogs);
        dogs.setImageResource(R.drawable.dog2);
    }
}
