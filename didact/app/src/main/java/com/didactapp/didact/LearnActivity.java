package com.didactapp.didact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.test.TestActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class LearnActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyDLygp6RSS5ind1zJ9puZvPfzAKJYaWWWQ";
    public static String VIDEO_ID ="AOAtz8xWM0w";
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
        toolbar.setTitle("Learn");
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        String exercise = getIntent().getStringExtra("exercise");
        MyDBHandler handler = new MyDBHandler(getApplicationContext(), "didact_db",1);
        VIDEO_ID = handler.getVideoId(exercise);
        youTubePlayerView.initialize(API_KEY, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.learn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        });
    }

}
