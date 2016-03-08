package com.didactapp.didact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.exercise.ExerciseActivity;
import com.didactapp.didact.subject.SubjectContent;
import com.didactapp.didact.subject.SubjectFragment;

public class MainActivity extends AppCompatActivity implements SubjectFragment.OnSubjectInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSubjecttInteraction(SubjectContent.Subject item) {
        LogUtils.d("item number " + item.id + " was pressed");
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("id",item.id);
        intent.putExtra("subject", item.content);
        startActivity(intent);

    }
}
