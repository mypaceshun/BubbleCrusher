package com.toshiki.shun.bubblecrusher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by toshiki on 2018/03/10.
 * 実際にゲームをプレイするビュー
 * 内部にCircle型のリストを持っており、それをすべて描画する
 * @author shun
 */

public class PlayView extends View {
    private ArrayList<VanishCircle> circles;
    private int score = 0;

    public PlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circles = new ArrayList<VanishCircle>();
    }
    public PlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PlayView(Context context){
        this(context, null);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // 拡張for文というちょっと特殊な書き方
        // (参照) https://qiita.com/Mocacamo/items/290457f9d378ad80ddba
        // for(int i = 0; i < circles.length; i++) {
        //    circles[i];
        // }
        // とするのと同じ意味
        for(VanishCircle circle : this.circles) {
            circle.onDraw(canvas);
        }
    }
    /**
     * 円の追加を行う
     * 引数に渡されたCircleのインスタンスを内部のリストに追加する
     * @param circle ビューに追加する円
     */
    public void addCircle(VanishCircle circle) {
        this.circles.add(circle);
    }
    public void addRandCircle(float radius) {
        float margin = radius;
        float MaxY = this.getHeight() - margin * 2;
        float MaxX = this.getWidth() - margin * 2;
        float randx = (float)Math.random() * MaxX + margin;
        float randy = (float)Math.random() * MaxY + margin;
        VanishCircle c = new VanishCircle(randx, randy, radius);
        this.addCircle(c);
    }
    /**
     * 初期化処理を行う
     * 1, リスト内の円をすべて削除
     * 2. scoreリセット
     */
    public void init() {
        this.circles.clear();
        this.score = 0;
    }
    /**
     * ステップ関数
     * 内部に持っているCircleすべてのステップ関数を呼びだす
     */
    public void step() {
        if(this.circles.size() < 1)
            return;
        for(Circle circle: this.circles) {
                circle.step();
        }
        // visibleフラグがfalseになっている円はすべて削除
        for(int i = circles.size() -1; i < 0; i--)
           if(circles.get(i).visible == false)
               circles.remove(i);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int count = event.getPointerCount();
        for (int i = 0; i < count; i++) {
            for (VanishCircle c : this.circles) {
                if (c.isInner(event.getX(i), event.getY(i))) {
                    c.touch();
                    this.score += 1 * c.rate;
                }
            }
        }
        Log.d("TouchEvent", "x: " + String.valueOf(event.getX(0)));
        Log.d("TouchEvent", "y: " + String.valueOf(event.getY(0)));
        return false;
    }
    public int getScore(){ return this.score; }
}
