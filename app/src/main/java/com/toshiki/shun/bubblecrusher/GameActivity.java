package com.toshiki.shun.bubblecrusher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by toshiki on 2018/03/10.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler = new Handler();
    private long count = 0;
    private int period = 1000;
    private int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.init();
    }
    public void init(){
        count = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                count++;
                Log.d("count", "count :" + count);
                if (count > limit) {
                    changeResultLayout();
                }
                handler.postDelayed(this, period);
            }
        }, period);

    }
    public void changeResultLayout(){
        handler.removeCallbacksAndMessages(null);
        setContentView(R.layout.activity_result);

    }
    @Override
    public void onClick(View v) {

    }
}
