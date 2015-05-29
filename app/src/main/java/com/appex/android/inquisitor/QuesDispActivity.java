package com.appex.android.inquisitor;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class QuesDispActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences count = getSharedPreferences(MainFragment.PREFS_FILE, 0);
        int mcount = count.getInt("count", MainFragment.mstartcount);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_disp);
        getSupportActionBar().hide();


        String ques[] = {
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
        if(mcount==1) {
            for (int i = 0; i < ques.length; i++) {
                ContentValues values = new ContentValues();
                values.put(QuestionsProvider.QUESTION, (ques[i]));

                Uri uri = getContentResolver().insert(QuestionsProvider.CONTENT_URI, values);

                //Toast.makeText(getBaseContext(),
                //uri.toString(), Toast.LENGTH_LONG).show();
            }
        }

        TextView qView=(TextView)findViewById(R.id.qview);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/Infinity.ttf");
        qView.setTypeface(typeface);
        String URL = "content://com.appex.android.inquisitor.QuestionsProvider/questions";
        Uri quesURI = Uri.parse(URL);
        Cursor c = managedQuery(quesURI, null, null, null, "_id");
        if (c.moveToFirst()) {
            do{
                qView.append(
                        c.getString(c.getColumnIndex("_id")) +
                                ": " + c.getString(c.getColumnIndex("question"))+"\n");
            } while (c.moveToNext());
        }
    }
}


