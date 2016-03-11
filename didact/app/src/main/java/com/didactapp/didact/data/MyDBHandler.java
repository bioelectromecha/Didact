package com.didactapp.didact.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apkfuns.logutils.LogUtils;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by roman on 06/03/16.
 */
public class MyDBHandler extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "didact_db";
    private static final String TABLE_NAME = "syllabus";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public MyDBHandler(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public String[] getSubjects() {
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
        String query = "SELECT DISTINCT exercise FROM "+ TABLE_NAME + " WHERE subject="+"'"+subject+"'";
        Cursor c = db.rawQuery(query, null);
        int numOfExercises = c.getCount();
        String[] exercises = new String[numOfExercises];
        c.moveToFirst();
        for(int i=0;i< numOfExercises;i++){
            exercises[i]=c.getString(0);
            c.moveToNext();
        }
        return exercises;
    }
    public String getVideoId(String exercise) {
        String query = "SELECT DISTINCT vid_id FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String video_id = c.getString(0);
        return video_id;
    }
    public String  getExplanation(String exercise){
        String query = "SELECT DISTINCT explanation FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String explanation = c.getString(0);
        return explanation;
    }
    public String getQuestion(String exercise){
        String query = "SELECT DISTINCT question FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String question = c.getString(0);
        return question;
    }
    public String getOption1(String exercise){
        String query = "SELECT DISTINCT option1 FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String option1 = c.getString(0);
        return option1;
    }
    public String getOption2(String exercise){
        String query = "SELECT DISTINCT option2 FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String getOption2 = c.getString(0);
        return getOption2;
    }    public String getOption3(String exercise){
        String query = "SELECT DISTINCT option3 FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String getOption3 = c.getString(0);
        return getOption3;
    }
    public String getOption4(String exercise){
        String query = "SELECT DISTINCT option4 FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String getOption4 = c.getString(0);
        return getOption4;
    }
    public int getAnswer(String exercise){
        String query = "SELECT DISTINCT answer FROM "+ TABLE_NAME + " WHERE exercise="+"'"+exercise+"'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int answer  = c.getInt(0);
        return answer;
    }


}
