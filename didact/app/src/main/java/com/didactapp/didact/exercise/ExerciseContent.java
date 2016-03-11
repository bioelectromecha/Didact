package com.didactapp.didact.exercise;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;
import com.didactapp.didact.data.MyDBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */

public class ExerciseContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Exercise> ITEMS = new ArrayList<Exercise>();
    public static boolean firstTime = true;


    public static List<Exercise> addItems(Context context, String subject) {
        if(firstTime == false){
            ITEMS.clear();
        }
        MyDBHandler db = new MyDBHandler(context, "didact_db",1);
        String[] exercises = db.getExercises(subject);
        for(int i=0;i< exercises.length;i++) {
            LogUtils.d(exercises[i]);
            ITEMS.add(new ExerciseContent.Exercise(String.valueOf(i), exercises[i], exercises[i]));
        }
        firstTime = false;
        return ITEMS;
    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class Exercise {
        public final int imageId = R.mipmap.ic_launcher;
        public final String id;
        public final String content;
        public final String details;

        public Exercise(String id, String content, String details) {

            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
