package com.gbreed.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    // 0: yellow, 1: red
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive)
        {
            counter.setTranslationY(-1500);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Someone has won if conditions met

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    //Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    winnerText.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout aGridLayout = findViewById(R.id.gridLayout);

       for(int i = 0; i < aGridLayout.getChildCount(); i++)
       {
        ImageView child = (ImageView) aGridLayout.getChildAt(i);

        child.setImageResource(0);
        }

        activePlayer = 0;
        gameActive = true;

        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2;
    }
}
