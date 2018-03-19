/**
 * Created by Yolanda Alonso Barbero on 3/4/18.
 */
package com.cs211d.drawcirclesapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class CirclesView extends View
{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<MyCircle> circlesList = new ArrayList<>();

    private final int MIN_COLOR = 0;
    private final int MAX_COLOR = 255;
    private final int MIN_RADIUS = 100;
    private final int MAX_RADIUS = getWidth() / 2;

    /************************** CirclesView() ************************/
    public CirclesView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
    }

    /********************* onDraw(Canvas canvas) *********************/
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for (MyCircle circle : circlesList)
        {
            paint.setColor(circle.getColor());
            canvas.drawCircle(
                    circle.getX(),
                    circle.getY(),
                    circle.getRadius(),
                    paint
            );
        }
    }

    /************************** drawCircle() *************************/
    /* Creates a new MyCircle with random color, location and size   */
    public void drawCircle()
    {
        int color = Color.rgb(
                rand(MIN_COLOR, MAX_COLOR),
                rand(MIN_COLOR, MAX_COLOR),
                rand(MIN_COLOR, MAX_COLOR)
        );
        int radius = rand(MIN_RADIUS, MAX_RADIUS);
        int x = rand(radius, getWidth() - radius);
        int y = rand(radius, getHeight() - radius);

        circlesList.add(new MyCircle(color, radius, x, y));
        postInvalidate();
    }

    /************************ removeCircles() ************************/
    public void removeCircles()
    {
        circlesList.clear();
        postInvalidate();
    }

    /***************************** rand() ****************************/
    private int rand(int a, int b)
    {
        return ((int) ((b - a + 1) * Math.random() + a));
    }
}