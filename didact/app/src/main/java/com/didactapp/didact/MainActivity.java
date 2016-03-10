package com.didactapp.didact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.dummy.DummyContent;
import com.didactapp.didact.exercise.ExerciseActivity;
import com.didactapp.didact.ranking.RankingFragment;
import com.didactapp.didact.subject.SubjectContent;
import com.didactapp.didact.subject.SubjectFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        SubjectFragment.OnSubjectInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        RankingFragment.OnListFragmentInteractionListener,
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    ViewPager viewPager;
    TabHost tabHost;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);
    }

    //viewpage listener
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //tabhost listener
    @Override
    public void onTabChanged(String tabId) {
        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()-
                (hScrollView.getWidth()-tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos,0 );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);

        initViewPager();
        initTabHost();
    }

    private void initTabHost(){
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        String[] tabNames = {"Subjects", "Profile", "Rankings"};

        for(int i=0; i<tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    private class FakeContent implements TabHost.TabContentFactory{

        Context mcontext;

        public FakeContent(Context context){
            mcontext=context;
        }
        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(mcontext);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void initViewPager(){
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add( new SubjectFragment());
        listFragments.add(new ProfileFragment());
        listFragments.add(new RankingFragment());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
