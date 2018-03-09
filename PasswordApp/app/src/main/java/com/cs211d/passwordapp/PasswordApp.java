//  Author:  Yolanda Alonso Barbero
//  Date:    02/23/2018
//  Homework assignment:  4
//  Objective: This program has two Activities. In the first
//             activity the user enters a password that is sent
//             to the second activity. The password is validated.
//             If it is incorrect an error is displayed.
//             Otherwise, the user can input a numeric value
//             to convert temperature:
//              - Fahrenheit to Celsius(°F -> °C)
//              - Celsius to Fahrenheit(°C -> °F)
//***************************************************************

package com.cs211d.passwordapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class PasswordApp extends Activity
{
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_password);
        etPassword = (EditText)
                findViewById(R.id.edit_tx_password);
    }

    public void submitPassword(View view)
    {
        String inputPassword = etPassword.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("password", inputPassword);
        Intent i = new Intent(this,
                TemperatureConversion.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
