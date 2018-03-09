//  Author:  Yolanda Alonso Barbero
//  Date:    02/09/2018
//  Homework assignment:  3
//  Objective: This program has two 'RadioButton' and the user
//             can input a numeric value inside 'EditText'.
//             It can convert temperature:
//              - Fahrenheit to Celsius(°F -> °C)
//              - Celsius to Fahrenheit(°C -> °F)
//***************************************************************

package com.cs211d.temperatureconversion;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TemperatureConversion extends Activity
{

    private enum ConversionType
    {
        FAHREN_TO_CELSIUS,
        CELSIUS_TO_FAHREN
    }

    EditText etx;
    TextView txtResult;
    ConversionType conversionType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        // when app starts default conversion to celsius
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
}
