package com.gbreed.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)");

            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Nick', 28)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Greg', 27)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Tom', 17)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Jim', 14)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Greg', 43)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Greg', 43)");
            //myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Greg', 43)");

            myDatabase.execSQL("DELETE FROM newUsers WHERE id = 3");

            Cursor c = myDatabase.rawQuery("SELECT * FROM newUsers", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (!c.isAfterLast()) {
                Log.i("Name", c.getString(nameIndex));
                Log.i("Age", c.getString(ageIndex));
                Log.i("Age", c.getString(idIndex));
                c.moveToNext();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
