package com.example.mycalls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//This class is for the adapter that is used in the call log calls
//The purpose of this class is to get the data from the database and act as an adapter to fill the space in the one_line_call_log layout
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    //Initializing list, context and data base handler
    List<CallEntry> allCallEntries;
    Context context;
    DBHandler dbHandler;

    //Constructor method
    public RecyclerViewAdapter(List<CallEntry> allCallEntries,Context context) {
        this.allCallEntries = allCallEntries;
        this.context = context;
    }
    //Method to get the size of the data
    @Override
    public int getItemCount() {
        return allCallEntries.size();
    }

    //Initializing the item from one_line_call_log layout to put data into
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCallType, tvContactName, tvPhNumber,tvDate,tvTime,tvDuration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCallType = itemView.findViewById(R.id.tvCallType);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvPhNumber = itemView.findViewById(R.id.tvPhNumber);
        }
    }

    //On creation of the adapter class it links to the layout and create and holder object
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_call_log,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //Sets the value on the holder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvPhNumber.setText(allCallEntries.get(position).getPhNumber());
        holder.tvDuration.setText(getStringDurationFromInt(allCallEntries.get(position).getCallDuration()));
        holder.tvCallType.setText(allCallEntries.get(position).getCallType());
        holder.tvDate.setText((allCallEntries.get(position).getCallDateTime()).substring(0,8));
        holder.tvTime.setText((allCallEntries.get(position).getCallDateTime()).substring(9));
        holder.tvContactName.setText(allCallEntries.get(position).getContactName());
    }

    //convert duration from integer to string (differentiating days, hours, minute and seconds)
    public String getStringDurationFromInt(int totalDuration){
        String ans =null;
        int daySec = 86400;
        int hourSec = 3600;
        int minuteSec = 60;

        if(totalDuration >= daySec){
            int days = (int) Math.floor(totalDuration / daySec);
            int hours = (int) Math.floor((totalDuration - days * daySec)/hourSec);
            int minutes = (int) Math.floor((totalDuration  - days * daySec - hours * hourSec)/minuteSec);
            int seconds = totalDuration - days * daySec - hours * hourSec - minutes * minuteSec;

            ans = (days + " day " +hours + " hrs " + minutes + " min " + seconds + " sec");
        }
        else if (totalDuration >= 3600){
            int days = (int) Math.floor(totalDuration / daySec);
            int hours = (int) Math.floor((totalDuration - days * daySec)/hourSec);
            int minutes = (int) Math.floor((totalDuration  - days * daySec - hours * hourSec)/minuteSec);
            int seconds = totalDuration - days * daySec - hours * hourSec - minutes * minuteSec;

            ans = (hours + " hrs " + minutes + " min " + seconds + " sec");
        }else if(totalDuration >= 60){
            int minutes = (int) Math.floor(totalDuration / 60);
            int seconds = seconds = totalDuration - minutes * 60;
            ans = (minutes + " min " + seconds + " sec");
        } else{
            ans = (totalDuration+ "sec");
        }

        return ans;
    }

}
