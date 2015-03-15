package com.appex.android.inquisitor;

import android.content.Intent;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public String ques[]={
                "FD,RT,LT,BK,Turtle",
                "Sixth Sense on Paper"
        };
        public String ans[]={
                "logo",
                "pranav mistry"
        };
        public String hint[]={
                "Programming",
                "Cameras and Projectors"
        };


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.activity_question, container, false);
            EditText t = (EditText) rootView.findViewById(R.id.edittext1);
            final TextView qView=(TextView)rootView.findViewById(R.id.textviewname);
            final TextView hView=(TextView)rootView.findViewById(R.id.hintview);
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
            Button HintButton=(Button)rootView.findViewById(R.id.hintbutton);
            HintButton.setOnClickListener(new View.OnClickListener() {
                int count=0;
                @Override
                public void onClick(View v) {
                    if(count==0) {
                        hView.append(hint[0]);
                    }
                    count++;
                }
            });
                return rootView;
            }
        }
    }
