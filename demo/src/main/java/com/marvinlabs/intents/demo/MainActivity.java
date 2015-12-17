package com.marvinlabs.intents.demo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;



public class MainActivity extends ActionBarActivity implements IntentItemFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new IntentItemFragment())
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Intent intent) {
        try{
            startActivity(intent);
        }catch(ClassNotFoundException e)
        {
            Toast.makeText(getApplicationContext(), "Error, try again later", Toast.LENGTH_LONG).show();
        }
    }
}
