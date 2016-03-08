package com.didactapp.didact.test;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;


public class TestActivity extends AppCompatActivity implements
        TestSingleFragment.OnFragmentInteractionListener,TestMultFragment.OnFragmentInteractionListener,
        TestTextFragment.OnFragmentInteractionListener, TestFillFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_layout);

        // You need to create the fragment based on the quiz type from the database
        //TestMultFragment fragment = new TestMultFragment();
        //TestSingleFragment fragment = new TestSingleFragment();
        // TestTextFragment fragment = new TestTextFragment();
        //TODO: add the question type to the app bar

        TestFillFragment fragment = new TestFillFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.test_fragment_container, fragment).commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.test_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Correct! good job", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        LogUtils.d("i click on fragment listener lololol");
    }

}
