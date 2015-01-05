package com.tech.technionevents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aiman.ay.28 on 3/1/15.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    Context mContext;
    Event[] mEvents;

    EventsAdapter(Context context, Event[] events){
        mContext = context;
        mEvents = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.event_row, parent, false);
        return new EventViewHolder(view);    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.title.setText(mEvents[position].getmTitle());
        holder.date.setText(mEvents[position].getmDate());
    }

    @Override
    public int getItemCount() {
        return mEvents.length;
    }

    class EventViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        public EventViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.eventTitle);
            date = (TextView)itemView.findViewById(R.id.eventDate);
        }
    }
}
