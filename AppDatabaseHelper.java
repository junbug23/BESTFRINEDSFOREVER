/* 
 * The following code was generated using generative AI and currently serves 
 * as a rough working draft. It will be cleaned up and adapted based on 
 * the specific needs of the project.
 */

/***************************************************************
* FILENAME: AppDatabaseHelper.java
* DESCRIPTION: SQLite database helper for managing tables and queries
* AUTHOR: Derek Morales
* DATE: 07/08/2025
* CHANGES: Initial version
****************************************************************/

package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/*
 * AppDatabaseHelper manages creation, upgrade, and basic operations 
 * on the local SQLite database.
 */
public class AppDatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "AppDatabase.db";
    private static final int DATABASE_VERSION = 1;

    /*
     * Constructor that initializes the database helper
     */
    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Called when the database is created for the first time.
     * Creates all required tables.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_TABLE);
        db.execSQL(EventTable.CREATE_TABLE);
        db.execSQL(UserEventTable.CREATE_TABLE);
    }

    /*
     * Called when the database needs to be upgraded.
     * Drops all tables and recreates them.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserEventTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EventTable.TABLE_NAME);
        onCreate(db);
    }

    /*
     * Inserts a new user into the users table.
     */
    public void insertUser(String id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_ID, id);
        values.put(UserTable.COLUMN_NAME, name);
        values.put(UserTable.COLUMN_EMAIL, email);
        db.insert(UserTable.TABLE_NAME, null, values);
        db.close();
    }

    /*
     * Returns a list of all user names from the users table.
     */
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserTable.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_NAME));
            users.add(name);
        }

        cursor.close();
        db.close();
        return users;
    }

    /**
     * Returns a list of event titles that a user with the given ID is attending.
     * This uses a JOIN between the events table and user_events mapping table.
     */
    public List<String> getEventsForUser(String userId) {
        List<String> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT e." + EventTable.COLUMN_TITLE +
                " FROM " + EventTable.TABLE_NAME + " e " +
                "JOIN " + UserEventTable.TABLE_NAME + " ue ON e." + EventTable.COLUMN_ID + " = ue." + UserEventTable.COLUMN_EVENT_ID +
                " WHERE ue." + UserEventTable.COLUMN_USER_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{userId});

        while (cursor.moveToNext()) {
            events.add(cursor.getString(0));
        }

        cursor.close();
        db.close();
        return events;
    }
}
