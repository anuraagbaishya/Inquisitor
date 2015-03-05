package com.appex.android.inquisitor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class QuestionFragment extends Fragment {
    public String ques[]={
            "FD,RT,LT,BK,Turtle",
            "Sixth Sense on Paper"
    };
    public String ans[]={
            "logo",
            "pranav mistry"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        EditText t = (EditText) rootView.findViewById(R.id.edittext1);
        TextView qView=(TextView)rootView.findViewById(R.id.textviewname);
        String ans1;
        //for(int i=0;i<2;i++) {
             //do {
                qView.append(ques[0]);
                ans1 = t.getText().toString();
                t.setGravity(Gravity.CENTER);
                if(ans1.equals(ans[0]))
                    qView.append(ques[1]);
            //} while (!(ans1.equals(ans[0])));
        //}
        return rootView;
    }
 }
