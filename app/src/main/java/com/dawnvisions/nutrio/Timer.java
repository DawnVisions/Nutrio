package com.dawnvisions.nutrio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Calendar;

import database.DataSource;
import model.Feeding;
import model.Weight;


public class Timer extends Fragment
{
    DataSource database;

    //Used for setting the timer again after a pause or stop
    private long timeWhenStopped= 0;

    String feedingSide;
    String feedingTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Breastfeeding Timer");

        Main activity = (Main)getActivity();
        database = activity.mDataSource;

        final Chronometer chronometer = view.findViewById(R.id.timer_view);
        final FloatingActionButton LeftStartFab = view.findViewById(R.id.start_left_fab);
        final FloatingActionButton RightStartFab = view.findViewById(R.id.start_right_fab);
        final FloatingActionButton pauseFab = view.findViewById(R.id.pause_fab);
        final FloatingActionButton stopFab = view.findViewById(R.id.stop_fab);
        final TextView startLeft = view.findViewById(R.id.start_left_text);
        final TextView startRight = view.findViewById(R.id.start_right_text);
        final TextView output = view.findViewById(R.id.output_text_view);
        final Button addButton = view.findViewById(R.id.add_to_log_button);

        //Start left button pressed
        LeftStartFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                RightStartFab.hide();
                pauseFab.show();
                stopFab.show();
                startRight.setText("");
                startLeft.setText("Left");
                output.setText("Timer running");
                addButton.setVisibility(View.INVISIBLE);
                feedingSide = "Left";
            }
        });

        //Start right button pressed
        RightStartFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                LeftStartFab.hide();
                pauseFab.show();
                stopFab.show();
                startRight.setText("Right");
                startLeft.setText("");
                output.setText("Timer running");
                addButton.setVisibility(View.INVISIBLE);
                feedingSide = "Right";
            }
        });

        //Pause button pressed
        pauseFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                chronometer.stop();
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                pauseFab.hide();
                stopFab.hide();
                output.setText("Timer paused");

            }
        });

        //Stop button pressed
        stopFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                chronometer.stop();
                timeWhenStopped = 0;
                feedingTime = chronometer.getText().toString();
                output.setText("Fed on the " + feedingSide + " for " + feedingTime);
                chronometer.setText("00:00");
                pauseFab.hide();
                stopFab.hide();
                LeftStartFab.show();
                RightStartFab.show();
                startLeft.setText(R.string.start_left);
                startRight.setText(R.string.start_right);
                addButton.setVisibility(View.VISIBLE);

            }
        });

        //Adds feeding to feeding log
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar today = Calendar.getInstance();
                String am_pm;
                if(today.get(Calendar.AM_PM) == Calendar.AM)
                {
                    am_pm = "am";
                }
                else
                {
                    am_pm = "pm";
                }

                Feeding newFeeding = new Feeding(
                        today.get(Calendar.YEAR),
                        today.get(Calendar.MONTH)+1,
                        today.get(Calendar.DATE),
                        today.get(Calendar.HOUR),
                        today.get(Calendar.MINUTE),
                        am_pm,
                        "Breast",
                        feedingTime,
                        feedingSide);
                database.createFeeding(newFeeding);

                output.setText("Feeding added to Feeding Log");
                addButton.setVisibility(View.INVISIBLE);
            }
        });
    }
}