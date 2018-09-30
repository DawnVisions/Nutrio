package com.dawnvisions.nutrio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class WeightTracker extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_tracker, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weight Tracker");

        final EditText enteredWeight = view.findViewById(R.id.enter_weight);
        final ToggleButton lbToggle = view.findViewById(R.id.off_lb_toggle);
        final TextView tv = view.findViewById(R.id.textView6);



        //Check from keyboard pressed, number entered
        enteredWeight.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(keyCode == KeyEvent.KEYCODE_ENTER )
                {
                    String s = enteredWeight.getText().toString();
                    if(s.matches(""))
                    {
                        Toast.makeText(v.getContext(), "Please enter a weight", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        double weight = 0;
                        weight = Double.parseDouble(enteredWeight.getText().toString());

                        //Entered grams
                        if (lbToggle.isChecked())
                        {
                            tv.setText("You entered " + weight + " grams");
                        } else //Entered lb, parse lb.oz to lb and oz
                        {
                            int weightLb = (int) weight;
                            String ounceStr = enteredWeight.getText().toString();
                            int weightOz = 0;
                            if (weight % 1 > 0)
                                weightOz = Integer.parseInt(ounceStr.substring(2));
                            tv.setText("You entered " + weightLb + "lb " + weightOz + "oz ");
                        }
                    }
                    return true;
                }
                return false;
            }
        });

    }
}