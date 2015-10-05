package com.appex.android.inquisitor.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appex.android.inquisitor.R;
import com.appex.android.inquisitor.database.DBHelper;
import com.appex.android.inquisitor.model.Question;
import com.appex.android.inquisitor.resources.Constants;
import com.appex.android.inquisitor.resources.UpdateQuestions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import chipset.potato.Potato;


public class StartActivity extends AppCompatActivity {
    public static final String PREFS_FILE = "PrefsFile";
    static int mstartcount=0;
    public static ArrayList<Question> mQuestionsList;
    public static DBHelper dbHelper;
    public static ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false))
            finish();
        dbHelper=new DBHelper(getApplicationContext());
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading Questions...");
        mProgressDialog.setCancelable(true);
        mQuestionsList=new ArrayList<>();
        setContentView(R.layout.activity_start);
        final TextView level = (TextView) findViewById(R.id.levelView);
        final TextView totattemptv = (TextView) findViewById(R.id.totAttempt);
        Button startButton = (Button) findViewById(R.id.startbutton);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Kankin.ttf");
        startButton.setTypeface(typeface);
        level.setTypeface(typeface);
        totattemptv.setTypeface(typeface);
        SharedPreferences startcount = getApplicationContext().getSharedPreferences(PREFS_FILE, 0);
        SharedPreferences count = getApplicationContext().getSharedPreferences(QuestionActivity.PREFS_FILE, 0);
        SharedPreferences totattempt = getApplicationContext().getSharedPreferences(QuestionActivity.PREFS_FILE, 2);
        int mcount = count.getInt("count", QuestionActivity.mcount);
        int mtotattempt = totattempt.getInt("totattempt", QuestionActivity.mTotalAttempt);
        mstartcount = startcount.getInt("count", mstartcount);
        level.setText("Levels Complete: " + mcount);
        totattemptv.setText("Attempts: " + mtotattempt);
        if (dbHelper.getAllQuestions().size() == 0) {
            if (Potato.potate().Utils().isInternetConnected(getApplicationContext()))
                prepareData();
            else {
                setContentView(R.layout.no_connection_layout);
                Button retryButton = (Button) findViewById(R.id.retry_button);
                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Potato.potate().Utils().isInternetConnected(getApplicationContext())) {
                            setContentView(R.layout.activity_start);
                            prepareData();
                        }
                    }
                });
            }
        }
        startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
                    overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_slide_up_disappear);
                }
            });
        }
        public void prepareData() {
        if(!mProgressDialog.isShowing())
            mProgressDialog.show();
        JsonObjectRequest quesRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_QUESTIONS, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Questions", response.toString());
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Question question = new Question();
                        question.setQuestionNo(data.getJSONObject(i).getInt("qno"));
                        question.setQuestion(data.getJSONObject(i).getString("question"));
                        question.setAnswer(data.getJSONObject(i).getString("answer"));
                        question.setHint(data.getJSONObject(i).getString("hint"));
                        dbHelper.insertQuestion(question);
                        mQuestionsList.add(question);
                        Log.d("Inserted",data.getJSONObject(i).getString("question"));
                    }
                    mProgressDialog.dismiss();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(quesRequest);
    }
}