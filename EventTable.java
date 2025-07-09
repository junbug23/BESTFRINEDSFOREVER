/* 
 * The following code was made with generative AI and is a very rough code at the moment. 
 * This code will be changed and cleaned up according to the project's needs.
 */

/***************************************************************
* FILENAME: EventTable.java
* DESCRIPTION: Defines the structure for the events table.
* AUTHOR: Derek Morales
* DATE: 07/08/2025
* CHANGES: N/A
****************************************************************/

package database;

/*
 * EventTable holds constants related to the 'events' table in the SQLite database,
 * including column names and the SQL command to create the table.
 */
public class EventTable {

    // Name of the table
    public static final String TABLE_NAME = "events";

    // Column names
    public static final String COLUMN_ID = "event_id";     // Primary key
    public static final String COLUMN_TITLE = "title";     // Event name/title
    public static final String COLUMN_DATE = "date";       // Event date (stored as text)

    // SQL command to create the events table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_DATE + " TEXT)";
}