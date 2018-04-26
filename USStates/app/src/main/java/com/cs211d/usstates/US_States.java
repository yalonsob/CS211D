//  Author:  Yolanda Alonso Barbero
//  Date:    03/28/2018
//  Homework assignment:  6
//  Objective: This program reads all the US states and capitals
//             from a text file saved as a resource in /res/raw.
//             It creates a SQLite database and us_states table.
//             The user can type a state or capital as input.
//             The output is the corresponding capital or state.
//*********************************************************************

package com.cs211d.usstates;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class US_States extends Activity
{
    private SQLiteDatabase myDB = null;
    private final String DB_NAME = "us_states_db.db";
    private final String TABLE_NAME = "us_states";

    private EditText etx;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        if (!existsDatabase()) createDatabase();
        else myDB = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        etx = (EditText) findViewById(R.id.edit_tx);
        txtResult = (TextView) findViewById(R.id.tx_result);
    }

    private boolean existsDatabase()
    {
        File dbFile = getDatabasePath(DB_NAME);
        return dbFile.exists();
    }

    public void cancel(View view)
    {
        etx.setText("");
        txtResult.setText("");
    }

    public void runUserQuery(View view)
    {
        String queryInput = etx.getText().toString().trim();
        String result = "";

        if (queryInput.equals(""))
        {
            txtResult.setText(result);
            return;
        }
        Cursor cursor;
        //Check if queryInput is capital and get its state
        cursor = myDB.rawQuery("SELECT state FROM us_states " +
                        "WHERE capital = ?",
                new String[]{queryInput.toUpperCase()});
        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String state = cursor.getString(0);
            result = queryInput.toUpperCase()
                    + " is the capital of " + state;
            txtResult.setText(result);
            return;
        }
        //Check if queryInput is state and get its capital
        cursor = myDB.rawQuery("SELECT capital FROM us_states " +
                        "WHERE state = ?",
                new String[]{queryInput.toUpperCase()});
        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String capital = cursor.getString(0);
            result = capital + " is the capital of "
                    + queryInput.toUpperCase();
            txtResult.setText(result);
            return;
        }
        // If we get here then queryInput is neither capital nor state
        result = "Invalid Input";
        txtResult.setText(result);
    }

    private void createDatabase()
    {
        //Create db
        myDB = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        //myDB.setLockingEnabled(true); //DEPRECATED
        myDB.setVersion(1);

        //Create table
        myDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "state VARCHAR, capital VARCHAR);");

        fillTableFromFile();
    }

    private void fillTableFromFile()
    {
        InputStream is = getResources().
                openRawResource(R.raw.us_states);
        Scanner scan = new Scanner(is);

        scan.nextLine(); // Line with titles
        scan.nextLine(); // Line with -----
        while (scan.hasNext())
        {
            String oneLine = scan.nextLine();
            Scanner lineScan = new Scanner(oneLine);
            lineScan.useDelimiter("(\\s\\s)+");
            String state = lineScan.next().toUpperCase().trim();
            String capital = lineScan.next().toUpperCase().trim();
            ContentValues cv = new ContentValues();
            cv.put("state", state);
            cv.put("capital", capital);
            myDB.insert(TABLE_NAME, null, cv);
        }
    }
}
