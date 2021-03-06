package com.dialerindia.vidu.dialerindia.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dialerindia.vidu.dialerindia.Activities.AutomaticCall;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.classes.Leads;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduledLeadsViewAdapter extends RecyclerView.Adapter<ScheduledLeadsViewAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<Leads> myProvider;


    public ScheduledLeadsViewAdapter(Context mContext, ArrayList<Leads> mProvider){
        this.myContext= mContext;
        this.myProvider = mProvider;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.scheduled_lead_recycle_card,parent,false);
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
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(currentLead.CallbackTime);
        String DateString ="Callback " +c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)+" "+c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) +" ";
        holder.Time.setText(DateString);
        boolean pending = currentLead.Pending;
        boolean missed = currentLead.Missed;
        if(pending){
            holder.Status.setText("P");
        }
        else if (missed){
            holder.Status.setText("N");
        }
        else{
            holder.Status.setText("A");
        }

        holder.CallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AutomaticCall().makecallbyContact(myContext, currentLead.Contact1, currentLead.id);
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
        TextView Name,Contact,Time, Status;
        ImageView CallNow, CallLater;
        MyViewHolder(View itemView) {
            super(itemView);
            ParentPanel = itemView.findViewById(R.id.parentPanel);
            Name = itemView.findViewById(R.id.Name);
            Contact = itemView.findViewById(R.id.Contact);
            Time = itemView.findViewById(R.id.time_txt);
            Status = itemView.findViewById(R.id.status);
            CallLater = itemView.findViewById(R.id.callLater);
            CallNow = itemView.findViewById(R.id.callNow);
        }

    }
}
