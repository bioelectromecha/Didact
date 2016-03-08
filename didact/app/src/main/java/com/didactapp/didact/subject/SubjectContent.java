package com.didactapp.didact.subject;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;
import com.didactapp.didact.MyDBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */

public class SubjectContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Subject> ITEMS = new ArrayList<Subject>();
    public static boolean firstTime = true;


    public static List<Subject> addItems(Context context) {
        if(firstTime == false){
            return ITEMS;
        }
        MyDBHandler db = new MyDBHandler(context, "didact_db",1);
        String[] subjects = db.getSubjects();
        for(int i=0;i<subjects.length;i++) {
            LogUtils.d(subjects[i]);
            ITEMS.add( new Subject(String.valueOf(i),subjects[i],subjects[i]) );
        }
        firstTime = false;
        return ITEMS;
    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class Subject {
        public final int imageId = R.mipmap.ic_launcher;
        public final String id;
        public final String content;
        public final String details;

        public Subject(String id, String content, String details) {

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
