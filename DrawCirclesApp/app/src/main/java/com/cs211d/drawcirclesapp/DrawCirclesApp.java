//  Author:  Yolanda Alonso Barbero
//  Date:    03/04/2018
//  Homework assignment:  5
//  Objective: This program has a big area to draw circles and
//             2 buttons, one to draw a new circle and one to
//             remove all circles.
//             Every circle is filled with a random color, and
//             is drawn in a random location with random size.
//*********************************************************************

package com.cs211d.drawcirclesapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class DrawCirclesApp extends Activity
{
    Button btnDrawCircle;
    Button btnClearCircles;
    CirclesView circlesView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        btnDrawCircle = (Button) findViewById(R.id.btn_draw);
        btnClearCircles = (Button) findViewById(R.id.btn_clear);
        circlesView = (CirclesView) findViewById(R.id.circles_view);

        btnDrawCircle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                circlesView.drawCircle();
            }
        });

        btnClearCircles.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                circlesView.removeCircles();
            }
        });
    }
}