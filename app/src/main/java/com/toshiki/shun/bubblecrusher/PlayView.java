package com.toshiki.shun.bubblecrusher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by toshiki on 2018/03/10.
 */

public class PlayView extends View {
    private Paint paint;
    private ArrayList<Circle> circles;

    public PlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

        circles = new ArrayList<Circle>();
    }
    public PlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PlayView(Context context){
        this(context, null);
    }
    public void onDraw(Canvas canvas) {
        for(Circle circle : this.circles) {
                canvas.drawCircle(circle.x, circle.y, circle.radius, paint);
        }
        Log.d("CustomOnClickListainer", "描画されたよ");
    }
    public void addCircle(Circle c) {
        this.circles.add(c);
    }
    public void init() {
        this.circles.clear();
        Circle initCircle = new Circle(100, 100, 10);
        this.addCircle(initCircle);
    }
    public void step() {
        for(Circle circle: this.circles) {
                circle.step();
        }
    }
}
