package com.appex.android.inquisitor.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appex.android.inquisitor.R;


public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        TextView eview=(TextView)findViewById(R.id.endview);
        eview.setGravity(Gravity.CENTER);
        Typeface typeface=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Kankin.ttf");
        eview.setTypeface(typeface);
    }
}
