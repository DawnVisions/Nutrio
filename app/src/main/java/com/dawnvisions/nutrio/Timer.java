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
import android.widget.Chronometer;
import android.widget.TextView;


public class Timer extends Fragment
{

    //Used for setting the timer again after a pause or stop
    private long timeWhenStopped= 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Breastfeeding Timer");

        final Chronometer chronometer = view.findViewById(R.id.timer_view);
        final FloatingActionButton LeftStartFab = view.findViewById(R.id.start_left_fab);
        final FloatingActionButton RightStartFab = view.findViewById(R.id.start_right_fab);
        final FloatingActionButton pauseFab = view.findViewById(R.id.pause_fab);
        final FloatingActionButton stopFab = view.findViewById(R.id.stop_fab);
        final TextView startLeft = view.findViewById(R.id.start_left_text);
        final TextView startRight = view.findViewById(R.id.start_right_text);
        final TextView output = view.findViewById(R.id.output_text_view);

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
                if(LeftStartFab.isOrWillBeHidden())
                {
                    output.setText("Fed on the Right for " + chronometer.getText());
                }
                else if(RightStartFab.isOrWillBeHidden())
                {
                    output.setText("Fed on the Left for " + chronometer.getText());
                }
                chronometer.setText("00:00");
                pauseFab.hide();
                stopFab.hide();
                LeftStartFab.show();
                RightStartFab.show();
                startLeft.setText(R.string.start_left);
                startRight.setText(R.string.start_right);

            }
        });
    }
}