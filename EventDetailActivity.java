/*
 * File: EventDetailActivity.java
 * Author: Sanskar Chaudhari
 * Date: July 8, 2025
 * Version: 1.0
 * Purpose: Displays details of a selected event, allows users to RSVP, and shows a list of attendees
 * with their names fetched from Firestore.
 */
package com.friendsforever.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.friendsforever.app.model.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

// EventDetailActivity displays event details and handles RSVP functionality
public class EventDetailActivity extends AppCompatActivity {
    // TextViews to display event details
    private TextView eventTitleTextView;
    private TextView eventDateTextView;
    private TextView eventLocationTextView;
    private TextView eventDescriptionTextView;
    // RecyclerView to display attendees
    private RecyclerView attendeesRecyclerView;
    // Button to RSVP to the event
    private Button rsvpButton;
    // Firestore instance for database operations
    private FirebaseFirestore firestoreDatabase;
    // Firebase Auth instance to get current user
    private FirebaseAuth firebaseAuth;
    // Event object to store event details
    private Event currentEvent;
    // List to store attendee names
    private List<String> attendeeNamesList;
    // Adapter for attendees RecyclerView
    private AttendeeAdapter attendeeAdapter;

    // Initialize variables at the beginning of the module
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call superclass onCreate
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_event_detail);

        // Initialize Firestore and Firebase Auth
        firestoreDatabase = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        // Initialize attendee names list
        attendeeNamesList = new ArrayList<>();

        // Initialize UI components
        eventTitleTextView = findViewById(R.id.textViewEventTitle);
        eventDateTextView = findViewById(R.id.textViewEventDate);
        eventLocationTextView = findViewById(R.id.textViewEventLocation);
        eventDescriptionTextView = findViewById(R.id.textViewEventDescription);
        attendeesRecyclerView = findViewById(R.id.recyclerViewAttendees);
        rsvpButton = findViewById(R.id.buttonRsvp);

        // Initialize RecyclerView for attendees
        attendeesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendeeAdapter = new AttendeeAdapter(attendeeNamesList);
        attendeesRecyclerView.setAdapter(attendeeAdapter);

        // Get event data from intent
        currentEvent = (Event) getIntent().getSerializableExtra("EVENT");
        if (currentEvent != null) {
            // Display event details
            eventTitleTextView.setText(currentEvent.getEventTitle());
            eventDateTextView.setText(currentEvent.getEventDate());
            eventLocationTextView.setText(currentEvent.getEventLocation());
            eventDescriptionTextView.setText(currentEvent.getEventDescription());

            // Load attendee names
            loadAttendeeNames();
        }

        // Set RSVP button click listener
        rsvpButton.setOnClickListener(view -> handleRsvp());
    }

    // Loads attendee names from Firestore based on participant IDs
    private void loadAttendeeList() {
        // Get participant IDs from event
        List<String> participants = currentEvent.getEventParticipants() != null ? 
            currentEvent.getEventParticipants() : new ArrayList<>();
        // Clear existing attendee names
        attendeeNamesList.clear();

        // Fetch user names for each participant
        for (String userId : participants) {
            firestoreDatabase.collection("users")
                    .document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        // Check if document exists
                        if (documentSnapshot.exists()) {
                            // Extract user name
                            String userName = documentSnapshot.getString("userName");
                            if (userName != null && !userName.isEmpty()) {
                                attendeeNamesList.add(userName);
                            } else {
                                attendeeNamesList.add("Unknown User");
                            }
                            // Notify adapter of changes
                            attendeeAdapter.notifyDataSetChanged();
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Add placeholder for failed fetch
                        attendeeNamesList.add("Failed to load User");
                        attendeeAdapter.notifyDataSetChanged();
                    });
        }
    }

    // Handles the RSVP action when the user clicks the RSVP button
    private void handleRsvp() {
        // Get the current user's ID
        String userIdentifier = firebaseAuth.getCurrentUser() != null ? 
            firebaseAuth.getCurrentUser().getUid() : null;
        if (userIdentifier == null) {
            // Show error if user is not logged in
            Toast.makeText(this, "Please log in to RSVP", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user has already RSVPed
        List<String> participants = currentEvent.getEventParticipants() != null ? 
            currentEvent.getEventParticipants() : new ArrayList<>();
        if (participants.contains(userIdentifier)) {
            // Prevent duplicate RSVPs
            Toast.makeText(this, "You have already RSVPed", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to participants list
        participants.add(userIdentifier);
        currentEvent.setEventParticipants(participants);

        // Update Firestore with new participants list
        firestoreDatabase.collection("events")
                .document(currentEvent.getEventIdentifier())
                .update("eventParticipants", participants)
                .addOnSuccessListener(aVoid -> {
                    // Notify user of successful RSVP
                    Toast.makeText(this, "RSVP successful", Toast.LENGTH_SHORT).show();
                    // Refresh attendee list
                    loadAttendeeNames();
                })
                .addOnFailureListener(e -> {
                    // Show error message if RSVP fails
                    Toast.makeText(this, "Failed to RSVP", Toast.LENGTH_SHORT).show();
                });
    }
}