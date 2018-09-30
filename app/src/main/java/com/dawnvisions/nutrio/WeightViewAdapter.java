package com.dawnvisions.nutrio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.Weight;

public class WeightViewAdapter extends RecyclerView.Adapter<WeightViewAdapter.ViewHolder>
{
    private List<Weight> weights;
    private Context mContext;

    public WeightViewAdapter(Context context, List<Weight> objects)
    {
        weights = objects;
        mContext = context;
    }

    @Override
    public WeightViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.weight_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeightViewAdapter.ViewHolder holder, int position)
    {
        Weight item = weights.get(position);
        holder.dateTv.setText(item.getMonth() + "/" + item.getDay() + "/" + item.getYear().toString().substring(2));
        holder.weightTv.setText(item.getPound() + "lb " + item.getOunce() + "oz ");
    }

    @Override
    public int getItemCount()
    {
        return weights.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView dateTv;
        public TextView weightTv;
        public ViewHolder(View itemView) {
            super(itemView);

            dateTv = itemView.findViewById(R.id.dateText);
            weightTv = itemView.findViewById(R.id.poundText);
        }
    }

    public void dataChanged(List<Weight> objects)
    {
        weights = objects;
    }
}