package com.dawnvisions.nutrio;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.List;

import database.DataSource;
import model.Weight;


public class WeightTracker extends Fragment
{

    DataSource database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_tracker, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weight Tracker");

        Main activity = (Main)getActivity();
        database = activity.mDataSource;

        final EditText enteredWeight = view.findViewById(R.id.enter_weight);
        final ToggleButton lbToggle = view.findViewById(R.id.off_lb_toggle);
        final TextView tv = view.findViewById(R.id.weight_output_text);


        //Binds recycler view to WeightViewAdapter for weights in the database
        final List<Weight> allWeights = database.getAllWeights();
        final WeightViewAdapter adapter = new WeightViewAdapter(view.getContext(), allWeights);
        RecyclerView recyclerView = view.findViewById(R.id.weight_recycler);
        recyclerView.setAdapter(adapter);


        //Checkmark from keyboard pressed, number entered
        enteredWeight.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN )
                {
                    //Hide the keyboard
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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
                            gramsToDatabase(weight);
                        }
                        else //Entered lb, parse lb.oz to lb and oz
                        {
                            int weightLb = (int) weight;
                            String ounceStr = enteredWeight.getText().toString();
                            int weightOz = 0;
                            if (weight % 1 > 0)
                                weightOz = Integer.parseInt(ounceStr.substring(2));
                            tv.setText("You entered " + weightLb + "lb " + weightOz + "oz ");
                            poundsToDatabase(weightLb, weightOz);
                        }
                    }
                    adapter.dataChanged(database.getAllWeights());
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean gramsToDatabase(Double weight)
    {
        //convert grams to lb and oz
        Integer gram = weight.intValue();
        Double ounceDouble = weight * 0.035274;
        Integer ounce = ounceDouble.intValue();
        Double poundDouble = Math.floor(ounceDouble/16);
        Integer pound = poundDouble.intValue();
        ounce = ounce % 16;

        Calendar today = Calendar.getInstance();

        Weight newWeight = new Weight(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH)+1,
                today.get(Calendar.DATE),
                gram,
                pound,
                ounce);
        database.createWeight(newWeight);

        return true;
    }

    private boolean poundsToDatabase(Integer pound, Integer ounce)
    {
        //convert pounds to oz
        Integer ounceToConvert = ounce + pound * 16;
        Integer gram = (int) Math.floor(ounceToConvert/0.035274);

        Calendar today = Calendar.getInstance();

        Weight newWeight = new Weight(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH)+1,
                today.get(Calendar.DATE),
                gram,
                pound,
                ounce);
        database.createWeight(newWeight);
        return true;
    }
}