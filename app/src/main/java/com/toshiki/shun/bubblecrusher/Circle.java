package com.toshiki.shun.bubblecrusher;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    public int color = Color.GREEN;
    /** 透明度 */
    public int alpha = 255;
    /** 削除フラグ */
    public boolean visible = true;
    /** エフェクトフラグ */
    public boolean crush = false;
    /** paint */
    private Paint paint;

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
        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
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

    /**
     *  指定された座標が円内にあるか判定
     * @param x x座標
     * @param y y座標
     * @return  円内ならtrue 円外ならfalse
     */
    public boolean isInner(float x, float y){
        float lenx = this.x - x;
        float leny = this.y - y;
        float len = (float)Math.sqrt(lenx * lenx + leny * leny);
        return len < this.radius;
    }
    public void touch() {
        this.crush = true;
    }
    public void onDraw(Canvas canvas) {
        if(this.crush)
            this.onCrushEffect();
        paint.setColor(this.color);
        paint.setAlpha(this.alpha);
        if(this.visible)
            canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
    public void onCrushEffect(){
        this.radius += 10;
        this.alpha -= 10;
        if(this.alpha <= 0){
            this.crush = false;
            this.visible = false;
        }
    }

}
