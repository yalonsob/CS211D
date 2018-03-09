//  Author:  Yolanda Alonso Barbero
//  Date:    02/02/2018
//  Homework assignment:  2
//  Objective: This program has two buttons.
//             One button shows the date and the other the time.
//***************************************************************

package com.cs211d.showdateandtime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Date;
import android.view.*;
import java.text.*;

public class ShowDateAndTime extends Activity
{
    TextView txtDate, txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        txtDate = (TextView) this.findViewById(R.id.tx_date);
        txtTime = (TextView) this.findViewById(R.id.tx_time);
    }

    private void updateDate()
    {
        Date date = new Date();
        String stringDate = DateFormat
                .getDateInstance(DateFormat.FULL).format(date);
        txtDate.setText(stringDate);
    }

    private void updateTime()
    {
        Date time = new Date(new Date().getTime());
        String stringTime = new SimpleDateFormat("HH:mm:ss")
                .format(time);
        txtTime.setText(stringTime);
    }

    public void updateDateOrTime(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_show_date:
                updateDate();
                break;
            case R.id.bt_show_time:
                updateTime();
                break;
        }
    }
}
