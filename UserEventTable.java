/*
 * The following code was made with generative AI and is a very rough code at the moment. 
 * This code will be changed and cleaned up according to the project's needs.
 */

/***************************************************************
* FILENAME: UserEventTable.java
* DESCRIPTION: Defines a many-to-many relationship between users and events.
* AUTHOR: Derek Morales
* DATE: 07/08/2025
* CHANGES: N/A
****************************************************************/

package database;

/*
 * UserEventTable represents a junction table that maps users to events.
 * This defines a many-to-many relationship between users and events.
 */
public class UserEventTable {

    // Table name
    public static final String TABLE_NAME = "user_events";

    // Foreign key columns
    public static final String COLUMN_USER_ID = "user_id";     // References users.user_id
    public static final String COLUMN_EVENT_ID = "event_id";   // References events.event_id

    /*
     * SQL statement to create the user_events table.
     * This table maps each user to the events they are attending.
     * It enforces referential integrity using foreign keys.
     */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_USER_ID + " TEXT, " +
            COLUMN_EVENT_ID + " TEXT, " +
            "PRIMARY KEY (" + COLUMN_USER_ID + ", " + COLUMN_EVENT_ID + "), " +
            "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.COLUMN_ID + "), " +
            "FOREIGN KEY(" + COLUMN_EVENT_ID + ") REFERENCES " + EventTable.TABLE_NAME + "(" + EventTable.COLUMN_ID + "))";
}
