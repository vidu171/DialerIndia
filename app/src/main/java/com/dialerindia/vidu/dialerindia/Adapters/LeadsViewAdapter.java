package com.dialerindia.vidu.dialerindia.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.classes.Leads;

import java.util.ArrayList;

public class LeadsViewAdapter  extends RecyclerView.Adapter<LeadsViewAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<Leads> myProvider;


    public  LeadsViewAdapter(Context mContext, ArrayList<Leads> mProvider){
        this.myContext= mContext;
        this.myProvider = mProvider;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_recycle_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(position%2!=0){
            holder.ParentPanel.setBackgroundColor(myContext.getResources().getColor(R.color.bluewhite));
        }
        final Leads currentLead = myProvider.get(position);
        holder.Name.setText(currentLead.Name);
        holder.Contact.setText(currentLead.Contact1);
        holder.City.setText(currentLead.City);
        boolean pending = currentLead.Pending;
        boolean missed = currentLead.Pending;
        if(pending){
            holder.StatusImage.setImageDrawable(myContext.getDrawable(R.drawable.ic_call_black_24dp));
        }
        else if (!pending && !missed){

            holder.StatusImage.setImageDrawable(myContext.getDrawable(R.drawable.ic_call_end_black_24dp));
        }
        else{

            holder.StatusImage.setImageDrawable(myContext.getDrawable(R.drawable.ic_call_missed_black_24dp));
        }
        holder.CallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,"Call Now",Toast.LENGTH_LONG).show();
            }
        });

        holder.CallLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,"Call Later",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return myProvider.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout ParentPanel;
        TextView Name,Contact,City;
        ImageView StatusImage, CallNow, CallLater;
        MyViewHolder(View itemView) {
            super(itemView);
            ParentPanel = itemView.findViewById(R.id.parentPanel);
            Name = itemView.findViewById(R.id.Name);
            Contact = itemView.findViewById(R.id.Contact);
            City = itemView.findViewById(R.id.City);
            StatusImage = itemView.findViewById(R.id.status);
            CallLater = itemView.findViewById(R.id.callLater);
            CallNow = itemView.findViewById(R.id.callNow);
        }

    }
}
