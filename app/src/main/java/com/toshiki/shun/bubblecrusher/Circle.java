package com.toshiki.shun.bubblecrusher;

/**
 * Created by toshiki on 2018/03/11.
 */

public class Circle {
    public int x;
    public int y;
    public int vx;
    public int vy;
    public int ax;
    public int ay;
    public int radius;
    public int color;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y= y;
        this.radius = radius;
    }
    public void step() {
        this.x += this.vx;
        this.y += this.vy;
        this.vx += this.ax;
        this.vy += this.ay;
    }
}
