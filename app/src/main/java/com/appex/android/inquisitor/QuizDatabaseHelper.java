package com.appex.android.inquisitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
public class QuizDatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "QuizDB";
    private static final String TABLE_QUESTION = "question";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_CHK = "chk";
    private static final String[] COLUMNS = {KEY_ID,KEY_QUESTION,KEY_CHK};

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        //String CREATE_QUESTION = ;

        // create books table
        db.execSQL("CREATE TABLE question (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS question");

        this.onCreate(db);
    }
    public void addQuestion(Quiz qa){
        Log.d("addQuestion", qa.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, qa.getQues());
        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }
    public Quiz getQuiz(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_QUESTION, COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Quiz qz=new Quiz();
        qz.setId(Integer.parseInt(cursor.getString(0)));
        qz.setQues(cursor.getString(1));


        return qz;
    }
    public ArrayList<Quiz> getAllQuestions() {
        ArrayList<Quiz> questions = new ArrayList<Quiz>();

        String query = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Quiz qz = null;
        if (cursor.moveToFirst()) {
            do {
                qz = new Quiz();
                qz.setId(Integer.parseInt(cursor.getString(0)));
                qz.setQues(cursor.getString(1));
                questions.add(qz);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", questions.toString());
        return questions;
    }
    public int updateQuiz(Quiz qz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", qz.getQues());
        int i = db.update(TABLE_QUESTION, values, KEY_ID+" = ?", new String[] {
                String.valueOf(qz.getId())
        });
        db.close();

        return i;

    }

}
