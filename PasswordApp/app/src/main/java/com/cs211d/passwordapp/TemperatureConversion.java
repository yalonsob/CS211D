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

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TemperatureConversion extends Activity
{
    final String VALID_PASSWORD = "cs211d";
    EditText etx;
    TextView txtResult;
    ConversionType conversionType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(chooseConversorView())
        {
            conversionType = ConversionType.FAHREN_TO_CELSIUS;
            txtResult = (TextView) findViewById(R.id.tx_result);
            etx = (EditText) findViewById(R.id.edit_tx_temp);
            etx.setOnKeyListener(new View.OnKeyListener()
            {
                @Override
                public boolean onKey(View view, int i, KeyEvent ke)
                {
                    if ((ke.getAction() == ke.ACTION_DOWN)
                            && (i == ke.KEYCODE_ENTER))
                    {
                        handleTemperatureConversion();
                    }
                    return false;
                }
            });
        }
    }

    // It gets the password and decides which layout to show.
    // It returns true if the password is correct, false otherwise.
    private boolean chooseConversorView()
    {
        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            String password = bundle.getString("password");

            if(password.equals(VALID_PASSWORD))
            {
                setContentView(R.layout.main_conversion);
                return true;
            }
            else
            {
                setContentView(R.layout.main_error);
                return false;
            }
        }
        else
        {
            setContentView(R.layout.main_error);
            return false;
        }
    }

    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.rbtn_to_celsius:
                if (checked)
                {
                    conversionType = ConversionType
                            .FAHREN_TO_CELSIUS;
                    handleTemperatureConversion();
                }
                break;
            case R.id.rbtn_to_fahrenheit:
                if (checked)
                {
                    conversionType = ConversionType
                            .CELSIUS_TO_FAHREN;
                    handleTemperatureConversion();
                }
                break;
        }
    }

    private void handleTemperatureConversion()
    {
        String strTemperature = etx.getText().toString();
        String result = "";

        if (strTemperature.equals(""))
        {
            txtResult.setText(result);
            return;
        }

        double temp = Double.parseDouble(strTemperature);

        if (conversionType.equals(ConversionType.FAHREN_TO_CELSIUS))
        {
            double celsius = convertFahrenheitToCelsius(temp);
            result = String.valueOf(temp) + "°F = "
                    + String.valueOf(celsius) + "°C";
        }
        if (conversionType.equals(ConversionType.CELSIUS_TO_FAHREN))
        {
            double fahrenheit = convertCelsiusToFahrenheit(temp);
            result = String.valueOf(temp) + "°C = "
                    + String.valueOf(fahrenheit) + "°F";
        }

        txtResult.setText(result);
    }

    private double convertFahrenheitToCelsius(double fahrenheit)
    {
        double celsius = (fahrenheit - 32) * 5 / 9.0;
        return Math.round(celsius * 100) / 100.0d;
    }

    private double convertCelsiusToFahrenheit(double celsius)
    {
        double fahrenheit = (celsius * 9 / 5.0) + 32;
        return Math.round(fahrenheit * 100) / 100.0d;
    }

    private enum ConversionType
    {
        FAHREN_TO_CELSIUS,
        CELSIUS_TO_FAHREN
    }
}
