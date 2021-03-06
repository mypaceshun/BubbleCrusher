package com.toshiki.shun.bubblecrusher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Created by toshiki on 2018/03/10.
 * Game画面とResult画面を表示するアクティビティ
 * 画面遷移は読み込むレイアウトファイルを切り替えることで実装
 * @author shun
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler = new Handler();
    private int clock = 0; // ms
    private Runnable run;

    private long startTime;
    private long endTime;
    private long limitTime = 20 * 1000; // ms


    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("ss.SS", Locale.JAPAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }
    /**
     * Game画面の初期化処理を実装
     * 具体的に行っている処理は以下の通り
     * 1, Gameレイアウトの読み込み
     * 2, タイマーのリセット
     * 3, 各種ビューの初期化(timerText, PlayView)
     * 4, スレッドのスタート
     */
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

                // 0.1秒ごとに円を追加するテスト
                if((int)remainTime % 10 < 1 ) { // ピッタリ0になる可能性が低いので許容範囲を与えている
                        Circle c = new Circle(500, 100, 100);
                        float rand = (int)remainTime / 10 % 20 - 10;
                        c.vx = rand;
                        c.vy = rand;
                        c.ay = (float)0.3;
                        playView.addCircle(c);
                }
                playView.step();
                playView.invalidate();
                if (remainTime <= 0) {
                    changeResultLayout(playView.getScore());
                }else {
                    handler.postDelayed(this, clock);
                }
            }
        };
        handler.postDelayed(run, clock);

    }
    /**
     * Result画面への切り替えを行う
     */
    public void changeResultLayout(int score){
        handler.removeCallbacks(run);
        run = null;
        setContentView(R.layout.activity_result);
        Button retry_buttom = (Button)findViewById(R.id.retry_botton);
        EditText score_number = (EditText) findViewById(R.id.score_numberView);
        score_number.setText(String.valueOf(score));
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
