package com.didactapp.didact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apkfuns.logutils.LogUtils;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by roman on 06/03/16.
 */
public class MyDBHandler extends SQLiteAssetHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "didact_db";
    public static final String TABLE_NAME = "syllabus";

    public MyDBHandler(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String[] getSubjects() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DISTINCT subject FROM "+ TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        int numOfSubjects = c.getCount();
        String[] subjects = new String[numOfSubjects];
        c.moveToFirst();
        for(int i=0;i<numOfSubjects;i++){
            subjects[i]=c.getString(0);
            LogUtils.d(subjects[i]);
            c.moveToNext();
        }
        return subjects;
    }
    public String[] getExercises(String subject) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DISTINCT exercise FROM "+ TABLE_NAME + " WHERE subject="+"'"+subject+"'";
        LogUtils.d(query);
        Cursor c = db.rawQuery(query, null);
        int numOfExercises = c.getCount();
        String[] exercises = new String[numOfExercises];
        c.moveToFirst();
        for(int i=0;i< numOfExercises;i++){
            exercises[i]=c.getString(0);
            LogUtils.d(exercises[i]);
            c.moveToNext();
        }
        return exercises;
    }
    public String getVideoId(String exercise) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DISTINCT vid_id FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        LogUtils.d(query);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String video_id = c.getString(0);
        return video_id;
    }

}
