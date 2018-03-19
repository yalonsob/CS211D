/**
 * Created by Yolanda Alonso Barbero on 3/4/18.
 */
package com.cs211d.drawcirclesapp;

public class MyCircle
{
    private int color;
    private int radius;
    private int x;
    private int y;

    /*************************** MyCircle() **************************/
    public MyCircle(int color, int radius, int x, int y)
    {
        this.color = color;
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    /********************** Getters and Setters **********************/
    public int getColor()
    {
        return color;
    }
    public void setColor(int color)
    {
        this.color = color;
    }
    public int getRadius()
    {
        return radius;
    }
    public void setRadius(int radius)
    {
        this.radius = radius;
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}