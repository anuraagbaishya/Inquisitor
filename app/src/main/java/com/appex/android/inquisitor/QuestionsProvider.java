package com.appex.android.inquisitor;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class QuestionsProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.appex.android.inquisitor.QuestionsProvider";
    static final String URL = "content://com.appex.android.inquisitor.QuestionsProvider/questions";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String QUESTION="question";

    private static HashMap<String, String> QUESTIONS_PROJECTION_MAP;

    static final int QUESTIONS = 1;
    static final int QUESTION_ID = 2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "questions", QUESTIONS);
        uriMatcher.addURI(PROVIDER_NAME, "questions/#", QUESTION_ID);
    }
    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuizDB";
    private static final String TABLE_QUESTION = "questions";
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TABLE_QUESTION +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " question TEXT NOT NULL);";
    public class QuizDatabaseHelper extends SQLiteOpenHelper {

        public QuizDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS questions");

            this.onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        QuizDatabaseHelper dbHelper = new QuizDatabaseHelper(context);
        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE_QUESTION, "", values);
        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_QUESTION);

        switch (uriMatcher.match(uri)) {
            case QUESTIONS:
                qb.setProjectionMap(QUESTIONS_PROJECTION_MAP);
                break;
            case QUESTION_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == ""){

            sortOrder = _ID;
        }
        Cursor c = qb.query(db,	projection,	selection, selectionArgs,
                null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case QUESTIONS:
                count = db.delete(TABLE_QUESTION, selection, selectionArgs);
                break;
            case QUESTION_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TABLE_QUESTION, _ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case QUESTIONS:
                count = db.update(TABLE_QUESTION, values,
                        selection, selectionArgs);
                break;
            case QUESTION_ID:
                count = db.update(TABLE_QUESTION, values, _ID +
                        " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case QUESTIONS:
                return "vnd.android.cursor.dir/vnd.appex.android.inquisitor.questions";
            /**
             * Get a particular student
             */
            case QUESTION_ID:
                return "vnd.android.cursor.item/vnd.appex.android.inquisitor.questions";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

}