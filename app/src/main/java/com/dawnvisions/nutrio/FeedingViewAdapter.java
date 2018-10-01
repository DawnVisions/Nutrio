package com.dawnvisions.nutrio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.Feeding;

public class FeedingViewAdapter extends RecyclerView.Adapter<FeedingViewAdapter.ViewHolder>
{
    private List<Feeding> feedings;
    private Context mContext;

    public FeedingViewAdapter(Context context, List<Feeding> objects)
    {
        feedings = objects;
        mContext = context;
    }

    @Override
    public FeedingViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.feeding_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedingViewAdapter.ViewHolder holder, int position)
    {
        String time;
        String am_pm = "am";

        Feeding item = feedings.get(position);

        //Changing from military time
        if(item.getHour() > 12 ) {
            Integer timeInt = (item.getHour() - 12);
            time = timeInt.toString() + ":" + String.format("%02d", item.getMin());
            am_pm = "pm";
        }
        else if(item.getHour() == 0) {
            time = "12:" + item.getMin();
        }
        else if(item.getHour() == 12) {
            am_pm = "pm";
            time = item.getHour().toString() + ":" + item.getMin().toString();
        }
        else {
            time = item.getHour().toString() + ":" + item.getMin().toString();
        }

        holder.dateTv.setText(item.getMonth() + "/" + item.getDay() + "/" + item.getYear().toString().substring(2));
        holder.timeTv.setText(time);
        holder.am_pmTv.setText(am_pm);
        holder.typeTv.setText(item.getType());
        holder.amountTv.setText(item.getAmount());
        holder.sideTv.setText(item.getSide());
    }

    @Override
    public int getItemCount()
    {
        return feedings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView dateTv;
        public TextView timeTv;
        public TextView am_pmTv;
        public TextView typeTv;
        public TextView amountTv;
        public TextView sideTv;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTv = itemView.findViewById(R.id.feeding_date);
            timeTv = itemView.findViewById(R.id.feeding_time);
            am_pmTv = itemView.findViewById(R.id.feeding_ampm);
            typeTv = itemView.findViewById(R.id.feeding_type);
            amountTv = itemView.findViewById(R.id.feeding_amount);
            sideTv = itemView.findViewById(R.id.feeding_side);
        }
    }

    public void dataChanged(List<Feeding> objects)
    {
        feedings = objects;
    }
}