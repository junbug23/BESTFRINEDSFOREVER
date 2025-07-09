/*
 * File: Event.java
 * Author: Sanskar Chaudhari
 * Date: July 8, 2025
 * Version: 1.0
 * Purpose: Defines the Event class to represent an event's data structure for the Friends Forever app,
 * including validation for all attributes to ensure data integrity.
 */
package com.friendsforever.app.model;

import java.util.ArrayList;
import java.util.List;

// Event class to represent an event's data structure
public class Event {
    // Unique identifier for the event
    private String eventIdentifier;
    // Title of the event (e.g., "Campus Coffee Meetup")
    private String eventTitle;
    // Date and time of the event in MM/dd/yyyy HH:mm format
    private String eventDate;
    // Location of the event (e.g., "University Center Room 101")
    private String eventLocation;
    // Description of the event (e.g., "A casual meetup for coffee lovers")
    private String eventDescription;
    // List of user identifiers who have RSVPed to the event
    private List<String> eventParticipants;

    // Default constructor required for Firestore deserialization
    public Event() {
        // Initialize attributes to acceptable default values
        this.eventIdentifier = "";
        this.eventTitle = "";
        this.eventDate = "";
        this.eventLocation = "";
        this.eventDescription = "";
        this.eventParticipants = new ArrayList<>();
    }

    // Parameterized constructor with data validation
    public Event(String identifier, String title, String date, String location, String description, List<String> participants) {
        // Validate and set event identifier
        if (identifier != null && !identifier.trim().isEmpty()) {
            this.eventIdentifier = identifier;
        } else {
            this.eventIdentifier = "";
        }

        // Validate and set event title
        if (title != null && !title.trim().isEmpty()) {
            this.eventTitle = title;
        } else {
            this.eventTitle = "";
        }

        // Validate and set event date (basic format check for MM/dd/yyyy HH:mm)
        if (date != null && date.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}")) {
            this.eventDate = date;
        } else {
            this.eventDate = "";
        }

        // Validate and set event location
        if (location != null && !location.trim().isEmpty()) {
            this.eventLocation = location;
        } else {
            this.eventLocation = "";
        }

        // Validate and set event description
        if (description != null && !description.trim().isEmpty()) {
            this.eventDescription = description;
        } else {
            this.eventDescription = "";
        }

        // Validate and set event participants
        if (participants != null) {
            this.eventParticipants = participants;
        } else {
            this.eventParticipants = new ArrayList<>();
        }
    }

    // Getter for event identifier
    public String getEventIdentifier() {
        return eventIdentifier;
    }

    // Setter for event identifier with validation
    public void setEventIdentifier(String identifier) {
        if (identifier != null && !identifier.trim().isEmpty()) {
            this.eventIdentifier = identifier;
        } else {
            this.eventIdentifier = "";
        }
    }

    // Getter for event title
    public String getEventTitle() {
        return eventTitle;
    }

    // Setter for event title with validation
    public void setEventTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.eventTitle = title;
        } else {
            this.eventTitle = "";
        }
    }

    // Getter for event date
    public String getEventDate() {
        return eventDate;
    }

    // Setter for event date with validation
    public void setEventDate(String date) {
        if (date != null && date.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}")) {
            this.eventDate = date;
        } else {
            this.eventDate = "";
        }
    }

    // Getter for event location
    public String getEventLocation() {
        return eventLocation;
    }

    // Setter for event location with validation
    public void setEventLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.eventLocation = location;
        } else {
            this.eventLocation = "";
        }
    }

    // Getter for event description
    public String getEventDescription() {
        return eventDescription;
    }

    // Setter for event description with validation
    public void setEventDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.eventDescription = description;
        } else {
            this.eventDescription = "";
        }
    }

    // Getter for event participants
    public List<String> getEventParticipants() {
        return eventParticipants;
    }

    // Setter for event participants with validation
    public void setEventParticipants(List<String> participants) {
        if (participants != null) {
            this.eventParticipants = participants;
        } else {
            this.eventParticipants = new ArrayList<>();
        }
    }
}