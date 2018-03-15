package com.toshiki.shun.bubblecrusher;

/**
 * Created by toshiki on 2018/03/11.
 * 動く円のクラス
 * 位置座標、速度、加速度の成分を持つ
 */

public class Circle {
    /** x座標 */
    public int x = 0;
    /** y座標 */
    public int y = 0;
    /** 速度のx成分 */
    public int vx = 0;
    /** 速度のy成分 */
    public int vy = 0;
    /** 加速度のx成分 */
    public int ax = 0;
    /** 加速度のy成分 */
    public int ay = 0;
    /** 半径 */
    public int radius = 0;
    /** 色 */
    public int color = 0;

    /**
     * コンストラクト
     * @param x x座標
     * @param y y座標
     * @param radius 半径
     */
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y= y;
        this.radius = radius;
    }
    /**
     * コンストラクタ
     */
    public Circle() {
        this(0, 0, 0);
    }

    /**
     * 速度加速度に応じて位置座標を移動させる
     */
    public void step() {
        this.x += this.vx;
        this.y += this.vy;
        this.vx += this.ax;
        this.vy += this.ay;
    }
}
