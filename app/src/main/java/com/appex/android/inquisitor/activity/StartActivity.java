package com.appex.android.inquisitor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appex.android.inquisitor.R;
import com.appex.android.inquisitor.model.Question;


public class StartActivity extends AppCompatActivity {
    public static final String PREFS_FILE = "PrefsFile";
    static int mstartcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false))
            finish();
        setContentView(R.layout.activity_start);
        final TextView level=(TextView)findViewById(R.id.levelView);
        final TextView totattemptv=(TextView)findViewById(R.id.totAttempt);
        Button startButton = (Button) findViewById(R.id.startbutton);
        Typeface typeface=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Kankin.ttf");
        startButton.setTypeface(typeface);
        level.setTypeface(typeface);
        totattemptv.setTypeface(typeface);
        SharedPreferences startcount=getApplicationContext().getSharedPreferences(PREFS_FILE,0);
        SharedPreferences count=getApplicationContext().getSharedPreferences(QuestionActivity.PREFS_FILE, 0);
        SharedPreferences totattempt=getApplicationContext().getSharedPreferences(QuestionActivity.PREFS_FILE, 2);
        int mcount=count.getInt("count", QuestionActivity.mcount);
        int mtotattempt=totattempt.getInt("totattempt", QuestionActivity.mtotattempt);
        mstartcount=startcount.getInt("count",mstartcount);
        level.setText("Levels Complete: " +mcount );
        totattemptv.setText("Attempts: "+ mtotattempt);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
                overridePendingTransition(R.anim.animation_slide_up_appear,R.anim.animation_slide_up_disappear);
            }
        });
    }
}