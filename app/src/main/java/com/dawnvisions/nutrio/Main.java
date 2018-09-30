package com.dawnvisions.nutrio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import database.DataSource;

public class Main extends Navi
    implements NavigationView.OnNavigationItemSelectedListener
{
    public DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        mDataSource = new DataSource(this);
        mDataSource.open();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        return super.onNavigationItemSelected(menuItem);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mDataSource.open();
    }

}
