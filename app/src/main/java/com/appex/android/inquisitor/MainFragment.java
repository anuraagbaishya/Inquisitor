package com.appex.android.inquisitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment {
    public static final String PREFS_FILE = "PrefsFile";
    static int mstartcount=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView level=(TextView)rootView.findViewById(R.id.levelView);
        final TextView totattemptv=(TextView)rootView.findViewById(R.id.totAttempt);
        Button startButton = (Button) rootView.findViewById(R.id.startbutton);
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Kankin.ttf");
        startButton.setTypeface(typeface);
        level.setTypeface(typeface);
        totattemptv.setTypeface(typeface);
        SharedPreferences startcount=getActivity().getSharedPreferences(PREFS_FILE,0);
        SharedPreferences count=getActivity().getSharedPreferences(QuestionActivity.PREFS_FILE, 0);
        SharedPreferences totattempt=getActivity().getSharedPreferences(QuestionActivity.PREFS_FILE, 2);
        int mcount=count.getInt("count", QuestionActivity.PlaceholderFragment.mcount);
        int mtotattempt=totattempt.getInt("totattempt", QuestionActivity.PlaceholderFragment.mtotattempt);
        mstartcount=startcount.getInt("count",mstartcount);
        level.setText("Levels Complete: " +mcount );
        totattemptv.setText("Attempts: "+ mtotattempt);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences count=getActivity().getSharedPreferences(PREFS_FILE, 0);
                SharedPreferences.Editor editor= count.edit();
                mstartcount++;
                editor.putInt("count",mstartcount);
                editor.commit();
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}