package com.appex.android.inquisitor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appex.android.inquisitor.model.Question;

import java.util.ArrayList;

/**
 * Created by anuraag on 23/9/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Questions.db";
    public static final String QUESTION_TABLE_NAME="questions";
    public static final String COLUMN_ID="id";
    public static final String Q_NO="qno";
    public static final String QUESTION="ques";
    public static final String ANSWER="ans";
    public static final String HINT="hint";
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + QUESTION_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + Q_NO +" INTEGER,"
                + QUESTION + " TEXT," + ANSWER + " TEXT," + HINT + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME);
        onCreate(db);
    }
    public void insertQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean QuesExists = false;
        for (Question question1 : getAllQuestions()) {
            if (question1.getQuestionNo()==question.getQuestionNo()&&question1.getQuestion().equals(question.getQuestion()) &&question1.getAnswer().equals(question.getAnswer()) &&question1.getHint().equals(question.getHint())) {
                QuesExists = true;
                break;
            }
        }
        if (!QuesExists) {
            contentValues.put(Q_NO,question.getQuestionNo());
            contentValues.put(QUESTION, question.getQuestion());
            contentValues.put(ANSWER, question.getAnswer());
            contentValues.put(HINT,question.getHint());
            db.insert(QUESTION_TABLE_NAME, null, contentValues);
        }
    }
    public ArrayList<Question> getAllQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rows = db.rawQuery("SELECT * FROM " + QUESTION_TABLE_NAME, null);
        ArrayList<Question> questions = new ArrayList<Question>();
        if (rows != null) {
            for (int i = 0; i < rows.getCount(); i++) {
                rows.moveToPosition(i);
                Question question = new Question();
                question.setQuestionNo(rows.getInt(1));
                question.setQuestion(rows.getString(2));
                question.setAnswer(rows.getString(3));
                question.setHint(rows.getString(4));
                questions.add(question);
            }
        }
        return questions;
    }
    public void dropTables() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME);
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + QUESTION_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + Q_NO +" INTEGER,"
                + QUESTION + " TEXT," + ANSWER + " TEXT," + HINT + " TEXT);");
    }
}
