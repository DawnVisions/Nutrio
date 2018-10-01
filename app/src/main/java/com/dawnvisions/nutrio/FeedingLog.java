package com.dawnvisions.nutrio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import database.DataSource;
import model.Feeding;
import model.Weight;


public class FeedingLog extends Fragment
{
    DataSource database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Feeding Log");

        //Gets a reference to the database from the Main Activity
        Main activity = (Main)getActivity();
        database = activity.mDataSource;

        //Binds recycler view to FeedingViewAdapter for feedings in the database
        final List<Feeding> allFeedings = database.getAllFeedings();
        final FeedingViewAdapter adapter = new FeedingViewAdapter(view.getContext(), allFeedings);
        RecyclerView recyclerView = view.findViewById(R.id.feeding_recycler);
        recyclerView.setAdapter(adapter);
    }
}
