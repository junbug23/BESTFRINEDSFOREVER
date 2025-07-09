/*
 * File: EventAdapter.java
 * Author: Sanskar Chaudhari
 * Date: July 8, 2025
 * Version: 1.0
 * Purpose: Provides an adapter for RecyclerView to display event cards in the Friends Forever app,
 * with click handling to navigate to event details.
 */
package com.friendsforever.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerView.widget.RecyclerView;
import com.friendsforever.app.model.Event;

import java.util.List;
import java.util.function.Consumer;

// EventAdapter for displaying event cards in a RecyclerView
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    // List of events to display
    private List<Event> eventList;
    // Context for starting activities
    private Context applicationContext;
    // Consumer for handling item clicks
    private Consumer<Integer> onItemClickListener;

    // Constructor to initialize the adapter
    public EventAdapter(List<Event> events, Context context, Consumer<Integer> clickListener) {
        // Initialize class attributes
        this.eventList = events != null ? events : new ArrayList<>();
        this.applicationContext = context;
        this.onItemClickListener = clickListener;
    }

    // Creates a new ViewHolder for an event card
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the event card layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_card, parent, false);
        return new EventViewHolder(view);
    }

    // Binds event data to the ViewHolder
    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        // Get the event at the current position
        Event event = eventList.get(position);
        // Set event details in the ViewHolder
        holder.eventTitleTextView.setText(event.getEventTitle());
        holder.eventDateTextView.setText(event.getEventDate());
        holder.eventLocationTextView.setText(event.getEventLocation());

        // Set click listener for the event card
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.accept(position);
            }
        });
    }

    // Returns the total number of events
    @Override
    public int getEventCount() {
        return eventList.size();
    }

    // ViewHolder class for event card views
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        // TextViews for event details
        TextView eventTitleTextView;
        TextView eventDateTextView;
        TextView eventLocationTextView;

        // Constructor to initialize ViewHolder
        public EventViewHolder(View itemView) {
            // Initialize super class
            super(itemView);
            // Initialize TextViews
            eventTitleTextView = itemView.findViewById(R.id.textViewEventTitle);
            eventDateTimeTextView = itemView.findViewById(R.id.textViewEventDate);
            eventLocationTextView = itemView.findViewById(R.id.textViewEventLocation);
            }
        }
    }