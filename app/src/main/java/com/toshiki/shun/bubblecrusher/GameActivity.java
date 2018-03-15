package com.toshiki.shun.bubblecrusher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Created by toshiki on 2018/03/10.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler = new Handler();
    private int clock = 10; // ms
    private Runnable run;

    private long startTime;
    private long endTime;
    private long limitTime = 20 * 1000; // ms


    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss.SS", Locale.JAPAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }
    public void init(){
        setContentView(R.layout.activity_game);
        // Timer init
        startTime = System.currentTimeMillis();

        // Count text init
        TextView time_limit = (TextView)findViewById(R.id.timeLimit);
        time_limit.setText(dataFormat.format(limitTime));

        // PlayView init
        PlayView playView = (PlayView)findViewById(R.id.playView);
        playView.init();

        // run thread
        run = new Runnable() {
            @Override
            public void run() {
                // 残り時間の計算
                endTime = System.currentTimeMillis();
                long diffTime = endTime - startTime;
                long remainTime = limitTime - diffTime;
                if(remainTime < 0){
                        remainTime = 0;
                }


                TextView time_limit = (TextView)findViewById(R.id.timeLimit);
                time_limit.setText(dataFormat.format(remainTime));

                // PlayViewのリフレッシュ
                PlayView playView = (PlayView)findViewById(R.id.playView);
                playView.step();
                playView.invalidate();

                if (remainTime <= 0) {
                    changeResultLayout();
                }else {
                    handler.postDelayed(this, clock);
                }
            }
        };
        handler.postDelayed(run, clock);

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
