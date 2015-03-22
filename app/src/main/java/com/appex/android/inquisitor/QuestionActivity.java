package com.appex.android.inquisitor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id== R.id.action_about){
            startActivity(new Intent(this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        public String ques[]={
                "FD,RT,LT,BK,Turtle.",
                "Sixth Sense on Paper.",
                "White House, Jenny Craig Inc.",
                "11, Oranje.",
                "Perkins Engineering, Brown-Forman.",
                "Whenever windows opened, it was there. But it will soon disappear.",
                "Grand Theft Auto, Ranbir Kapoor.",
                "Hoomerpalooza, Lollapalooza.",
                "Was used in war, now a TV star."

        };
        public String ans[]={
                "logo",
                "pranav mistry",
                "monica samille lewinsky",
                "the flying dutchman",
                "jack daniel's",
                "internet explorer",
                "rockstar",
                "cypress hill",
                "arrow"
        };
        public String hint[]={
                "Programming",
                "Cameras and Projectors",
                "Scandal",
                "Davy Jones",
                "Old No.7",
                "Thomas Reardon",
                "It's a one word connection",
                "Ghetto Therapy",
                "Emanuel Chiroco"
        };
        int i=0;
        int j=0;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.activity_question, container, false);
            final EditText t = (EditText) rootView.findViewById(R.id.edittext1);
            final TextView qView=(TextView)rootView.findViewById(R.id.textviewname);
            final TextView hView=(TextView)rootView.findViewById(R.id.hintview);
            Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/1942.ttf");
            qView.setTypeface(typeface);
            hView.setTypeface(typeface);
            qView.append(ques[0]);
            t.setTypeface(typeface);
            t.setGravity(Gravity.CENTER);
            Button DoneButton=(Button)rootView.findViewById(R.id.done_button);
            DoneButton.setTypeface(typeface);
            DoneButton.setOnClickListener(new View.OnClickListener(){
                String ans1;
                public void onClick(View dview) {
                    for (i = 0; i < ques.length; i++) {
                        j=i+1;
                        ans1 = t.getText().toString();
                        if (ans1.equals(ans[i])) {
                            Toast.makeText(getActivity(),R.string.correcttoast,Toast.LENGTH_SHORT).show();
                            hView.setText("");
                            t.setText("");
                            if(j!=ques.length)
                                qView.setText(ques[j]);
                            else
                                qView.append("Congrats!You have completed the quiz");
                            break;
                        }
                    }
                }
            });
            Button HintButton=(Button)rootView.findViewById(R.id.hintbutton);
            HintButton.setTypeface(typeface);
            HintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hView.setText(hint[j]);
                }
            });
            return rootView;
        }
    }
}