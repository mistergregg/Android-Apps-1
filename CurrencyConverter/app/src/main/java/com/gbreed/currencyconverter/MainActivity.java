package com.gbreed.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertCurrency(View view)
    {
        EditText editText = findViewById(R.id.editTextConvert);

        Double textCur = Double.parseDouble(editText.getText().toString());

        Toast.makeText(this, String.format("%.2f", textCur) + " is $" + String.format("%.2f", textCur * 1.3), Toast.LENGTH_SHORT).show();
    }
}
