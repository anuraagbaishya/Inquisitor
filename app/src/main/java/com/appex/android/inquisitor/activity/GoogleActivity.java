package com.appex.android.inquisitor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appex.android.inquisitor.fragment.GoogleActivityFragment;
import com.appex.android.inquisitor.R;

public class GoogleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new GoogleActivityFragment())
                    .commit();
        }
    }
}
