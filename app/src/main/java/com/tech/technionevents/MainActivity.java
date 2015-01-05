package com.tech.technionevents;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
    implements NavigationDrawerFragment.CategorySelection {
    FragmentManager fragmentManager;

    private Toolbar toolbar;
    List<Event> events = new ArrayList<>();
    ArrayDeque<Event> eventArrayDequee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fragmentManager = getFragmentManager();
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                fragmentManager.findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), toolbar);
        events.add(new Event("aiman", "today"));
        eventArrayDequee = new ArrayDeque<>(events);
        if(savedInstanceState==null){
                createEventsFragment();
        } else {
            Fragment recycled=fragmentManager.findFragmentByTag(EventsListFragment.TAG);
            if(recycled==null){
                  createEventsFragment();
            } else{
                fragmentManager.beginTransaction().replace(R.id.main_container,recycled).addToBackStack(null).commit();
            }
        }

    }

    private void createEventsFragment(){
        EventsListFragment fragment = new EventsListFragment();
       Bundle bundle=new Bundle();
        bundle.putSerializable("ARG_EVENTS",eventArrayDequee);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment, EventsListFragment.TAG).addToBackStack(null).commit();
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
    public void onCategorySelected(String category) {
        EventsListFragment eventsFragment = (EventsListFragment)fragmentManager.findFragmentByTag(EventsListFragment.TAG);
        List<Event> events = new ArrayList<>();
        events.add(new Event(category, category));
        eventsFragment.changeEvents(events);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
    }
}
