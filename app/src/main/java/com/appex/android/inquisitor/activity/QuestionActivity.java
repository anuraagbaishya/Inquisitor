package com.appex.android.inquisitor.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appex.android.inquisitor.database.DBHelper;
import com.appex.android.inquisitor.model.Question;
import com.appex.android.inquisitor.resources.Constants;

import com.appex.android.inquisitor.R;
import com.appex.android.inquisitor.resources.UpdateQuestions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import chipset.potato.Potato;


public class QuestionActivity extends AppCompatActivity {
    public static final String PREFS_FILE = "MyPrefsFile";
    public static int mcount=0;
    public static int mAttempt=0;
    public static int mTotalAttempt=0;
    public static ArrayList<String> mQuestionList;
    ArrayList<String> mAnswerList;
    ArrayList<String> mHintList;
    public static ArrayList<Question> mQuestionsList;
    public static DBHelper dbHelper;
    public static Context mContext;
    String ans1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        dbHelper=new DBHelper(getApplicationContext());
        mQuestionList=new ArrayList<>();
        mAnswerList=new ArrayList<>();
        mHintList=new ArrayList<>();
        mQuestionsList=new ArrayList<>();
        final String error[]=Constants.ERROR;
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        if (dbHelper.getAllQuestions().size() != 0) {
//            if (Potato.potate().Utils().isInternetConnected(this)) {
//                UpdateQuestions updateQuestions = new UpdateQuestions();
//                updateQuestions.execute((String) null);
//            }
//        }
        mQuestionList=dbHelper.getQuestions();
        mAnswerList=dbHelper.getAnswers();
        mHintList=dbHelper.getHints();
        SharedPreferences count=getApplicationContext().getSharedPreferences(PREFS_FILE, 0);
        final SharedPreferences attempt=getApplicationContext().getSharedPreferences(PREFS_FILE,1);
        SharedPreferences totattempt=getApplicationContext().getSharedPreferences(PREFS_FILE, 2);
        mcount=count.getInt("count",mcount);
        mAttempt=attempt.getInt("attempt",mAttempt);
        mTotalAttempt=totattempt.getInt("totattempt",mTotalAttempt);
        final EditText answerEditText = (EditText)findViewById(R.id.answer_edit_text);
        final TextView questionTextView=(TextView)findViewById(R.id.question_text_view);
        final TextView hintTextView=(TextView)findViewById(R.id.hint_text_view);
        final TextView attemptTextView=(TextView)findViewById(R.id.attempt_text_view);
        final TextView levelTextView=(TextView)findViewById(R.id.level_text_view);
        Typeface typeface=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Infinity.ttf");
        questionTextView.setTypeface(typeface);
        hintTextView.setTypeface(typeface);
        attemptTextView.setTypeface(typeface);
        levelTextView.setTypeface(typeface);
        attemptTextView.setText("Attempts: "+mAttempt);
        if(mcount<mQuestionList.size()) {
            questionTextView.setText(mQuestionList.get(mcount));
            levelTextView.setText("Level: " + (mcount+1));
        }
        else{
            Intent intent = new Intent(getApplicationContext(), EndActivity.class);
            startActivity(intent);
        }
        answerEditText.setTypeface(typeface);
        answerEditText.setGravity(Gravity.CENTER);
        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ans1 = answerEditText.getText().toString();
                    if (ans1.trim().equalsIgnoreCase(mAnswerList.get(mcount)) && !(ans1.isEmpty())) {
                        mcount++;
                        mTotalAttempt++;
                        mAttempt=0;
                        attemptTextView.setText("Attempts: "+mAttempt);
                        Toast.makeText(getApplicationContext(), R.string.correcttoast, Toast.LENGTH_SHORT).show();
                        hintTextView.setText("");
                        answerEditText.setText("");
                        if(mcount<mQuestionList.size()){
                            questionTextView.setText(mQuestionList.get(mcount));
                            levelTextView.setText("Level: " + (mcount + 1));
                            }
                        else
                            startActivity(new Intent(getApplicationContext(),EndActivity.class));
                    } else {
                        int r = (int) (java.lang.Math.random() * 6);
                        answerEditText.setText("");
                        mAttempt++;
                        mTotalAttempt++;
                        attemptTextView.setText("Attempts: "+mAttempt);
                        Toast.makeText(getApplicationContext(), error[r], Toast.LENGTH_SHORT).show();
                    }
                    handled = true;
                }
                return handled;
            }
        });
        Button DoneButton=(Button)findViewById(R.id.done_button);
        DoneButton.setTypeface(typeface);
        DoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View dview) {
                ans1 = answerEditText.getText().toString();
                if (ans1.trim().equalsIgnoreCase(mAnswerList.get(mcount)) && !(ans1.isEmpty())) {
                    mcount++;
                    mTotalAttempt++;
                    mAttempt=0;
                    attemptTextView.setText("Attempts: "+mAttempt);
                    Toast.makeText(getApplicationContext(), R.string.correcttoast, Toast.LENGTH_SHORT).show();
                    hintTextView.setText("");
                    answerEditText.setText("");
                    if(mcount<mQuestionList.size()){
                            questionTextView.setText(mQuestionList.get(mcount + 1));
                            levelTextView.setText("Level: " + (mcount + 1));
                    }
                    else
                        startActivity(new Intent(getApplicationContext(),EndActivity.class));
                } else {
                    int r = (int) (java.lang.Math.random() * 6);
                    answerEditText.setText("");
                    mAttempt++;
                    mTotalAttempt++;
                    attemptTextView.setText("Attempts: "+mAttempt);
                    Toast.makeText(getApplicationContext(), error[r], Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button HintButton=(Button)findViewById(R.id.hintbutton);
        HintButton.setTypeface(typeface);
        HintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintTextView.setGravity(Gravity.CENTER);
                hintTextView.setText(mHintList.get(mcount));
            }
        });
        Button GoogleButton=(Button)findViewById(R.id.googlebutton);
        GoogleButton.setTypeface(typeface);
        GoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),GoogleActivity.class));
            }
        });
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
        editor.putInt("count",mcount);
        editor.putInt("attempt",mAttempt);
        editor.putInt("totattempt", mTotalAttempt);
        editor.apply();
        super.onStop();

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}