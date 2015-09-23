package com.appex.android.inquisitor.model;

/**
 * Created by anuraag on 23/9/15.
 */
public class Question {
    private String Question;
    private String Answer;
    private String Hint;

    public String getHint() {
        return Hint;
    }

    public void setHint(String hint) {
        Hint = hint;
    }

    public String getAnswer() {

        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getQuestion() {

        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
