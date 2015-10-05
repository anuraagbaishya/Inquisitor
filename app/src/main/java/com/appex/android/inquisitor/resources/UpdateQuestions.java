package com.appex.android.inquisitor.resources;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appex.android.inquisitor.activity.QuestionActivity;
import com.appex.android.inquisitor.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateQuestions extends AsyncTask<String,Void,String>{
    @Override
    protected String doInBackground(String... params){
        JsonObjectRequest quesRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_QUESTIONS, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                QuestionActivity.mQuestionList.clear();

                Log.d("Questions", response.toString());
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Question question = new Question();
                        question.setQuestionNo(data.getJSONObject(i).getInt("qno"));
                        question.setQuestion(data.getJSONObject(i).getString("question"));
                        question.setAnswer(data.getJSONObject(i).getString("answer"));
                        question.setHint(data.getJSONObject(i).getString("hint"));
                        QuestionActivity.dbHelper.insertQuestion(question);
                        QuestionActivity.mQuestionsList.add(question);
                        Log.d("Inserted",data.getJSONObject(i).getString("question"));
                    }
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
        Volley.newRequestQueue(QuestionActivity.mContext).add(quesRequest);
        return null;
    }
}

