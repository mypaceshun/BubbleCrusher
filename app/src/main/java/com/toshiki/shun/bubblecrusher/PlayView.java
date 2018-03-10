package com.toshiki.shun.bubblecrusher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by toshiki on 2018/03/10.
 */

public class PlayView extends View {
    private Paint paint;
    public Circle circle;

    public PlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        circle = new Circle(100,100,100);
    }
    public PlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PlayView(Context context){
        this(context, null);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(circle.x, circle.y, circle.radius, paint);
        Log.d("CustomOnClickListainer", "描画されたよ");
    }
}
