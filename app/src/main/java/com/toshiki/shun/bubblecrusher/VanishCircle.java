package com.toshiki.shun.bubblecrusher;

import android.graphics.Color;

/**
 * Created by shun on 2018/03/31.
 */

public class VanishCircle extends Circle {
    public float rate;
    /**
     * コンストラクト
     * @param x x座標
     * @param y y座標
     * @param radius 半径
     */
    public VanishCircle(float x, float y, float radius) {
        super(x, y, radius);
        this.rate  = (float)1.0;
    }
    @Override
    public void step(){
        super.step();
        if(this.crush)
            return;
        if(this.radius <= 0.0) {
            this.radius = (float) 0.0;
            this.visible = false;
        } else {
            this.radius--;
        }

        // レートの更新
        if(this.radius < 50) {
            this.rate = (float) 5.0;
            this.color = Color.RED;
        } else if(this.radius < 150) {
            this.rate = (float) 2.0;
            this.color = Color.YELLOW;
        }
    }
}
