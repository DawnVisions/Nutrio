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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        final EditText date = view.findViewById(R.id.enter_feeding_date);
        final EditText time = view.findViewById(R.id.enter_feeding_time);
        final ToggleButton am_pmToggle = view.findViewById(R.id.am_pm_toggle);
        final ToggleButton typeToggle = view.findViewById(R.id.breast_bottle);
        final ToggleButton sideToggle = view.findViewById(R.id.Left_Right);
        final TextView amount = view.findViewById(R.id.enter_feeding_amount);
        final Button addFeeding = view.findViewById(R.id.add_manual_feeding_button);

        //Set date EditText and time EditText to current date
        Calendar today = Calendar.getInstance();
        date.setText(today.get(Calendar.MONTH)+1 + "/" + today.get(Calendar.DATE) + "/" + today.get(Calendar.YEAR));
        time.setText(today.get(Calendar.HOUR) + ":" + today.get(Calendar.MINUTE));
        am_pmToggle.setChecked(today.get(Calendar.AM_PM) == Calendar.PM);

        //Make side invisible if feeding type is Bottle
        sideToggle.setVisibility(view.INVISIBLE);
        typeToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(typeToggle.getText().toString().equals("Breast"))
                {
                    sideToggle.setVisibility(v.VISIBLE);
                }
                else
                {
                    sideToggle.setVisibility(v.INVISIBLE);
                }
            }
        });

        //Add feeding button pressed
        addFeeding.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(amount.getText().toString().equals(""))
                {
                    Toast.makeText(v.getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Parse date
                    String dateStr = date.getText().toString();
                    String[] dateParse = dateStr.split("\\/");

                    String timeStr = time.getText().toString();
                    String[] timeParse = timeStr.split("\\:");

                    String feedingSide;
                    if(typeToggle.getText().toString().equals("Bottle"))
                    {
                        feedingSide = "";
                    }
                    else
                    {
                        feedingSide = sideToggle.getText().toString();
                    }

                    Feeding newFeeding = new Feeding(
                            Integer.parseInt(dateParse[2]),
                            Integer.parseInt(dateParse[0]),
                            Integer.parseInt(dateParse[1]),
                            Integer.parseInt(timeParse[0]),
                            Integer.parseInt(timeParse[1]),
                            am_pmToggle.getText().toString(),
                            typeToggle.getText().toString(),
                            amount.getText().toString(),
                            feedingSide);
                    database.createFeeding(newFeeding);
                    adapter.dataChanged(database.getAllFeedings());
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }
}
