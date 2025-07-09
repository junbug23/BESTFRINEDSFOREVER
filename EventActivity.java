/*
 * File: EventActivity.java
 * Author: Sanskar Chaudhari
 * Date: July 8, 2025
 * Version: 1.0
 * Purpose: Displays a list of events in a RecyclerView for the Friends Forever app, fetching data
 * from Firestore and allowing users to view event details.
 */
package com.friendsforever.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.friendsforever.app.model.Event;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

// EventActivity displays a list of events in a RecyclerView
public class EventActivity extends AppCompatActivity {
    // RecyclerView to display event cards
    private RecyclerView eventsRecyclerView;
    // Adapter for the RecyclerView
    private EventAdapter eventAdapter;
    // List to store events fetched from Firestore
    private List<Event> eventList;
    // Firestore instance for database operations
    private FirebaseFirestore firestoreDatabase;

    // Initialize variables at the beginning of the module
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call superclass onCreate
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_event);

        // Initialize Firestore instance
        firestoreDatabase = FirebaseFirestore.getInstance();
        // Initialize the event list
        eventList = new ArrayList<>();
        // Initialize the RecyclerView
        eventsRecyclerView = findViewById(R.id.recyclerViewEvents);
        // Set a LinearLayoutManager for the RecyclerView
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize the adapter with the event list
        eventAdapter = new EventAdapter(eventList, this, position -> {
            // Handle item click to open EventDetailActivity
            Intent intent = new Intent(EventActivity.this, EventDetailActivity.class);
            intent.putExtra("EVENT", eventList.get(position));
            startActivity(intent);
        });
        // Set the adapter to the RecyclerView
        eventsRecyclerView.setAdapter(eventAdapter);

        // Load events from Firestore
        loadEventsFromFirestore();
    }

    // Fetches all events from Firestore and updates the RecyclerView
    private void loadEventsFromFirestore() {
        // Query the events collection
        firestoreDatabase.collection("events")
                .get()
                .addOnCompleteListener(task -> {
                    // Check if the task was successful
                    if (task.isSuccessful()) {
                        // Clear the current event list
                        eventList.clear();
                        // Iterate through the query results
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (com.google.firebase.firestore.DocumentSnapshot document : querySnapshot) {
                                // Convert each document to an Event object
                                Event event = document.toObject(Event.class);
                                if (event != null) {
                                    eventList.add(event);
                                }
                            }
                            // Notify the adapter of data changes
                            eventAdapter.notifyDataSetChanged();
                        }
                    } else {
                        // Show error message if fetching fails
                        Toast.makeText(EventActivity.this, "Failed to load events", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}