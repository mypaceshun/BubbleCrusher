package com.toshiki.shun.bubblecrusher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by toshiki on 2018/03/10.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler = new Handler();
    private long count = 0;
    private int period = 1000;
    private int limit = 10;
    private Runnable run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }
    public void init(){
        setContentView(R.layout.activity_game);
        count = 0;
        run = new Runnable() {
            @Override
            public void run() {
                count++;
                Log.d("count", "count :" + count);
                if (count >= limit) {
                    changeResultLayout();
                }else {
                    handler.postDelayed(this, period);
                }
            }
        };
        handler.postDelayed(run, period);

    }
    public void changeResultLayout(){
        handler.removeCallbacks(run);
        run = null;
        setContentView(R.layout.activity_result);
        Button retry_buttom = (Button)findViewById(R.id.retry_botton);
        retry_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }
    @Override
    public void onClick(View v) {

    }
}
