package com.didactapp.didact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.data.MyDBHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LearnActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyDLygp6RSS5ind1zJ9puZvPfzAKJYaWWWQ";
    public static String VIDEO_ID ="AOAtz8xWM0w";
    private String mExercise;
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        /** Start buffering **/
        LogUtils.d("initialization successs and wasRestored status is: " + wasRestored);
        LogUtils.d(VIDEO_ID);
        youTubePlayer.cueVideo(VIDEO_ID);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        LogUtils.d("initializtion failed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_learn);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);

        mExercise = getIntent().getStringExtra("exercise");
        MyDBHandler handler = new MyDBHandler(getApplicationContext(), "didact_db",1);
        VIDEO_ID = handler.getVideoId(mExercise);
        String explanation = handler.getExplanation(mExercise);
        handler.close();

        TextView textView = (TextView) findViewById(R.id.learn_text_view);
        textView.setText(explanation);

        youTubePlayerView.initialize(API_KEY, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.learn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), TestSingleActivity.class);
                intent2.putExtra("exercise", mExercise);
                LogUtils.d("Star Activity ");
                startActivity(intent2);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
