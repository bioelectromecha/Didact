package com.didactapp.didact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.data.MyDBHandler;

public class TestSingleActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private FloatingActionButton fab;
    int mAnswer;
    String mOption1;
    String mOption2;
    String mOption3;
    String mOption4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_single_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String exercise = getIntent().getStringExtra("exercise");
        MyDBHandler handler = new MyDBHandler(getApplicationContext(), "didact_db",1);

        mOption1 = handler.getOption1(exercise);
        mOption2 = handler.getOption2(exercise);
        mOption3 = handler.getOption3(exercise);
        mOption4 = handler.getOption4(exercise);
        mAnswer = handler.getAnswer(exercise);
        handler.close();

        RadioButton bt1 = (RadioButton)findViewById(R.id.radioButton1);
        RadioButton bt2= (RadioButton)findViewById(R.id.radioButton2);
        RadioButton bt3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton bt4 = (RadioButton) findViewById(R.id.radioButton4);

        bt1.setText(mOption1);
        bt2.setText(mOption2);
        bt3.setText(mOption3);
        bt4.setText(mOption4);

        fab = (FloatingActionButton) findViewById(R.id.single_fab);

        mRadioGroup =(RadioGroup)findViewById(R.id.test_radio_group);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                int id = mRadioGroup.indexOfChild(radioButton)+1;
                LogUtils.d("id " + id);
                if(id==mAnswer){
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_all_24dp));
                    Snackbar.make(view, "Correct! Good job!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_24dp));
                    Snackbar.make(view, "Wrong! try, try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

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
