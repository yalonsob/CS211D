//  Author:  Yolanda Alonso Barbero
//  Date:    01/28/2018
//  Homework assignment:  1
//  Objective: This program  is showing 3 lines
//             with different colors, center justified.
//***************************************************************

package com.cs211d.hellostudents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class HelloStudents extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }
}
