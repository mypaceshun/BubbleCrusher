package com.toshiki.shun.bubblecrusher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by toshiki on 2018/03/10.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler = new Handler();
    private long count = 0;
    private int period = 10;
    private int limit = 100;
    private Runnable run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }
    public void init(){
        setContentView(R.layout.activity_game);
        count = limit;
        TextView time_limit = (TextView)findViewById(R.id.timeLimit);
        time_limit.setText(String.valueOf(count));
        run = new Runnable() {
            @Override
            public void run() {
                count--;
                TextView time_limit = (TextView)findViewById(R.id.timeLimit);
                time_limit.setText(String.valueOf(count));
                PlayView playView = (PlayView)findViewById(R.id.playView);
                playView.circle.radius =(int) count*10;
                playView.invalidate();
                Log.d("count", "count :" + count);
                if (count <= 0) {
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
