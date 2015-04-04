package com.appex.android.inquisitor;

import android.content.Intent;
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


public class QuestionFragment extends Fragment {


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
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        Button StartButton = (Button) rootView.findViewById(R.id.startbutton);
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Kankin.ttf");
        TextView View=(TextView)rootView.findViewById(R.id.welcomemsg);
        View.setTypeface(typeface);
        StartButton.setTypeface(typeface);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }
}