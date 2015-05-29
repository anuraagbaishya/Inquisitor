package com.appex.android.inquisitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class QuestionActivity extends ActionBarActivity {
    public static final String PREFS_FILE = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.bgbar);
        getSupportActionBar().setBackgroundDrawable(drawable);
        getSupportActionBar().setElevation(0f);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
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

        if(id== R.id.action_about){
            startActivity(new Intent(this, AboutActivity.class));
        }
        if(id==R.id.action_share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharemsg));
            intent=Intent.createChooser(intent,getString(R.string.sendmsg));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStop(){
        SharedPreferences count=getSharedPreferences(PREFS_FILE,0);
        SharedPreferences attempt=getSharedPreferences(PREFS_FILE, 1);
        SharedPreferences totattempt=getSharedPreferences(PREFS_FILE, 2);
        SharedPreferences.Editor editor= count.edit();
        editor.putInt("count",PlaceholderFragment.mcount);
        editor.putInt("attempt",PlaceholderFragment.mattempt);
        editor.putInt("totattempt",PlaceholderFragment.mtotattempt);
        editor.commit();
        super.onStop();

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
                "Was used in war, now a TV star.",
                "Juiced by the National Research Corporation"

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
                "arrow",
                "minute maid"
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
                "Emanuel Chiroco",
                "Odwalla"
        };
        public String error[]={
                "Oops! That's Not Correct.",
                "That was close. Keep trying",
                "Getting closer...",
                "Try Google, maybe.",
                "Try Again!",
                "Just a little more effort."
        };

        static int mcount=0;
        static int mattempt=0;
        static int mtotattempt=0;
        String ans1;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.activity_question, container, false);
            SharedPreferences count=getActivity().getSharedPreferences(PREFS_FILE, 0);
            SharedPreferences attempt=getActivity().getSharedPreferences(PREFS_FILE,1);
            SharedPreferences totattempt=getActivity().getSharedPreferences(PREFS_FILE, 2);
            mcount=count.getInt("count",mcount);
            mattempt=attempt.getInt("attempt",mattempt);
            mtotattempt=totattempt.getInt("totattempt",mtotattempt);
            final EditText t = (EditText) rootView.findViewById(R.id.edittext1);
            final TextView qView=(TextView)rootView.findViewById(R.id.questionview);
            final TextView hView=(TextView)rootView.findViewById(R.id.hintview);
            final TextView aView=(TextView)rootView.findViewById(R.id.attemptview);
            final TextView lView=(TextView)rootView.findViewById(R.id.levelview);
            Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Infinity.ttf");
            qView.setTypeface(typeface);
            hView.setTypeface(typeface);
            aView.setTypeface(typeface);
            lView.setTypeface(typeface);
            if(mattempt!=0)
                aView.setText("Attempts: "+mattempt);
            if(mcount<ques.length) {
                qView.append(ques[mcount]);
                lView.setText("Level: " + (mcount+1));
            }
            else{
                Intent intent = new Intent(getActivity(), EndActivity.class);
                startActivity(intent);
            }
            t.setTypeface(typeface);
            t.setGravity(Gravity.CENTER);
            t.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        ans1 = t.getText().toString();
                        if (ans1.trim().equalsIgnoreCase(ans[mcount]) && !(ans1.isEmpty())) {
                            mcount++;
                            mtotattempt++;
                            mattempt=0;
                            aView.setText("");
                            Toast.makeText(getActivity(), R.string.correcttoast, Toast.LENGTH_SHORT).show();
                            hView.setText("");
                            t.setText("");
                            if (mcount != ques.length) {
                                qView.setText(ques[mcount]);
                                lView.setText("Level: " + (mcount+1));
                            }
                            else {
                                Intent intent = new Intent(getActivity(), EndActivity.class);
                                startActivity(intent);
                            }

                        } else {
                            int r = (int) (java.lang.Math.random() * 6);
                            t.setText("");
                            Toast.makeText(getActivity(), error[r], Toast.LENGTH_SHORT).show();
                            mattempt++;
                            mtotattempt++;
                            aView.setText("Attempts: "+mattempt);
                        }
                        handled = true;
                    }
                    return handled;
                }
            });
            Button DoneButton=(Button)rootView.findViewById(R.id.done_button);
            DoneButton.setTypeface(typeface);
            DoneButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View dview) {
                    ans1 = t.getText().toString();
                    if (ans1.trim().equalsIgnoreCase(ans[mcount]) && !(ans1.isEmpty())) {
                        mcount++;
                        mtotattempt++;
                        mattempt=0;
                        Toast.makeText(getActivity(), R.string.correcttoast, Toast.LENGTH_SHORT).show();
                        hView.setText("");
                        t.setText("");
                        if (mcount != ques.length) {
                            qView.setText(ques[mcount]);
                            lView.setText("Level: " + (mcount+1));
                        }
                        else {
                            Intent intent = new Intent(getActivity(), EndActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        int r = (int) (java.lang.Math.random() * 6);
                        t.setText("");
                        mattempt++;
                        mtotattempt++;
                        Toast.makeText(getActivity(), error[r], Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Button HintButton=(Button)rootView.findViewById(R.id.hintbutton);
            HintButton.setTypeface(typeface);
            HintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hView.setGravity(Gravity.CENTER);
                    hView.setText(hint[mcount]);
                }
            });
            Button GoogleButton=(Button)rootView.findViewById(R.id.googlebutton);
            GoogleButton.setTypeface(typeface);
            GoogleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent=new Intent(getActivity(),GoogleActivity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }
}