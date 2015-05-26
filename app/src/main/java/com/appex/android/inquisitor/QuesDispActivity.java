package com.appex.android.inquisitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class QuesDispActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences count=getSharedPreferences(MainFragment.PREFS_FILE, 0);
        int mcount=count.getInt("count", MainFragment.mstartcount);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_disp);
        QuizDatabaseHelper db = new QuizDatabaseHelper(this);
        getSupportActionBar().hide();
        if(mcount==1) {
            db.addQuestion(new Quiz("FD,RT,LT,BK,Turtle."));
            db.addQuestion(new Quiz("Sixth Sense on Paper."));
            db.addQuestion(new Quiz("White House, Jenny Craig Inc."));
            db.addQuestion(new Quiz("11, Oranje."));
            db.addQuestion(new Quiz("Perkins Engineering, Brown-Forman."));
            db.addQuestion(new Quiz("Whenever windows opened, it was there. But it will soon disappear."));
            db.addQuestion(new Quiz("Grand Theft Auto, Ranbir Kapoor."));
            db.addQuestion(new Quiz("Was used in war, now a TV star."));
            db.addQuestion(new Quiz("Juiced by the National Research Corporation"));
        }
        ArrayList<Quiz> question = db.getAllQuestions();
        // Create the adapter to convert the array to views
        QuestionAdapter adapter = new QuestionAdapter(this, question);
        // Attach the adapter to a ListView
        ListView listView = (ListView)findViewById(R.id.lvItems);
        listView.setAdapter(adapter);
    }
    public class QuestionAdapter extends ArrayAdapter<Quiz> {
        public QuestionAdapter(Context context, ArrayList<Quiz> questions) {
            super(context, 0, questions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Quiz quiz = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_list, parent, false);
            }
            Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/Infinity.ttf");
            TextView quesView = (TextView) convertView.findViewById(R.id.qlist);
            quesView.setTypeface(typeface);
            quesView.setText((position+1)+". "+quiz.getQues());
            return convertView;
        }

    }
}
