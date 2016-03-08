package com.didactapp.didact.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.didactapp.didact.LearnActivity;
import com.didactapp.didact.R;

public class ExerciseActivity extends AppCompatActivity implements ExerciseFragment.OnExerciseInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onExerciseInteraction(ExerciseContent.Exercise item) {
            Intent intent = new Intent(this, LearnActivity.class);
            intent.putExtra("exercise", item.content);
            startActivity(intent);
    }
}
