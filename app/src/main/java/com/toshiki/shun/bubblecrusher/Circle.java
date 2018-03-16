package com.toshiki.shun.bubblecrusher;

/**
 * Created by toshiki on 2018/03/11.
 * 動く円のクラス
 * 位置座標、速度、加速度の成分を持つ
 */

public class Circle {
    /** x座標 */
    public float x = 0;
    /** y座標 */
    public float y = 0;
    /** 速度のx成分 */
    public float vx = 0;
    /** 速度のy成分 */
    public float vy = 0;
    /** 加速度のx成分 */
    public float ax = 0;
    /** 加速度のy成分 */
    public float ay = 0;
    /** 半径 */
    public float radius = 0;
    /** 色 */
    public float color = 0;

    /**
     * コンストラクト
     * @param x x座標
     * @param y y座標
     * @param radius 半径
     */
    public Circle(float x, float y, float radius) {
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
