package com.gbreed.chooselanguage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    SharedPreferences sharedPreferences;
    TextView languageText;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.english:
                languageText.setText("English");
                sharedPreferences.edit().putString("language", "english").apply();
                return true;
            case R.id.spanish:
                languageText.setText("Spanish");
                sharedPreferences.edit().putString("language", "spanish").apply();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.gbreed.chooselanguage", Context.MODE_PRIVATE);
        languageText  = findViewById(R.id.languageText);
        final String language = sharedPreferences.getString("language", "");

        if(language.equals("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Language")
                    .setMessage("Which language do you want the app in?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            languageText.setText("English");
                            sharedPreferences.edit().putString("language", "english").apply();
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            languageText.setText("Spanish");
                            sharedPreferences.edit().putString("language", "spanish").apply();
                        }
                    })
                    .show();
        }else
        {
            if(language.equals("english"))
                languageText.setText("English");
            else
                if(language.equals("spanish"))
                    languageText.setText("Spanish");

        }
    }
}
