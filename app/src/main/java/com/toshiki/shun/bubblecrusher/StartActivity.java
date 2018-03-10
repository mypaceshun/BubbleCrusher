package com.toshiki.shun.bubblecrusher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StartButtonOnClickListener slistainer = new StartButtonOnClickListener();
        findViewById(R.id.start_button).setOnClickListener(slistainer);
    }

    class StartButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Log.d("CustomOnClickListainer", "クリックされたよ");
            Intent intent = new Intent(getApplication(), GameActivity.class);
            startActivity(intent);
        }
    }
}
