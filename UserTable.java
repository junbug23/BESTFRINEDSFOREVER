/*
 * The following code was made with generative AI and is a very rough code at the moment. 
 * This code will be changed and cleaned up according to the project's needs.
 */

/***************************************************************
* FILENAME: UserTable.java
* DESCRIPTION: Defines the structure and constants for the users table.
* AUTHOR: Derek Morales
* DATE: 07/08/2025
* CHANGES: N/A
****************************************************************/

package database;

/*
 * UserTable defines the schema for the 'users' table in the SQLite database.
 * It contains column constants and the SQL statement used to create the table.
 */
public class UserTable {

    // Table name
    public static final String TABLE_NAME = "users";

    // Column names
    public static final String COLUMN_ID = "user_id";     // Primary key
    public static final String COLUMN_NAME = "name";      // Full name of the user
    public static final String COLUMN_EMAIL = "email";    // Email address

    /*
     * SQL command to create the users table.
     * Each user must have a unique ID, name, and email.
     */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT)";
}