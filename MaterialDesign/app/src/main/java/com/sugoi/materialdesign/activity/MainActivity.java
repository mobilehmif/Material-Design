package com.sugoi.materialdesign.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sugoi.materialdesign.R;
import com.sugoi.materialdesign.fragment.FragmentDrawer;
import com.sugoi.materialdesign.fragment.FragmentFriends;
import com.sugoi.materialdesign.fragment.FragmentHome;
import com.sugoi.materialdesign.fragment.FragmentMessages;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar toolbar;
    private FragmentDrawer fragmentDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FragmentManager fm = getSupportFragmentManager();
        fragmentDrawer = (FragmentDrawer) fm.findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        fragmentDrawer.setDrawerListener(this);

        //display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            String to_toast = "Search action";
            Toast toast = Toast.makeText(getApplicationContext(), to_toast, Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                fragment = new FragmentHome();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FragmentFriends();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new FragmentMessages();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            getSupportActionBar().setTitle(title);
        }
    }
}
