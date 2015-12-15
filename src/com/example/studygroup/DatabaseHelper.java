package com.example.studygroup;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "GroupHolder";

	// Table Names
	private static final String TABLE_GROUP = "StudyGroup";
	public static final String TABLE_STUDENT = "Users";
	//private static final String TABLE_STUDENT_COURSE = "students_courses";

	// Table Student
	public static final String KEY_USER = "username";
	public static final String KEY_EMAIL = "email";

	//Table Groups
	private static final String KEY_CODE = "Course_Name";    
	private static final String KEY_LOCATION = "Location";
	private static final String KEY_MAXMEMBERS = "Max_Members";
	private static final String KEY_DESCRIPTION = "Description";


	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final String CREATE_TABLE_GROUP = "CREATE TABLE "
			+ TABLE_GROUP + "(" + KEY_CODE + " TEXT PRIMARY KEY, " + KEY_LOCATION
			+ " TEXT, " + KEY_DESCRIPTION + " TEXT, " + KEY_MAXMEMBERS + " INTEGER" + ")";

	public static final String CREATE_TABLE_STUDENT =
			"CREATE TABLE " + TABLE_STUDENT + "(" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL);";
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{

			db.execSQL(CREATE_TABLE_GROUP);
			db.execSQL(CREATE_TABLE_STUDENT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}    

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);

		onCreate(db);
	}

	public void createGroup(Groups group) {
		SQLiteDatabase db = this.getWritableDatabase();
		// String crs_id = group.getCourseID();
		System.out.println("Groups:" + group);
		ContentValues values = new ContentValues();
		values.put("Course_Name", group.getCourseID());
		values.put("Location", group.getLocation());
		values.put("Description", group.getDescription());
		values.put("Max_Members", group.getMaximumNumbers());

		// insert row
		db.insert(TABLE_GROUP, null, values);
		db.close();
	}

	/*
	 * get single group
	 */
	public Groups getGroup(String groupid) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_GROUP + " WHERE "
				+ KEY_CODE + " = " + groupid;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Groups td = new Groups();
		td.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
		td.setLocation((c.getString(c.getColumnIndex(KEY_LOCATION))));
		td.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
		td.setMaximumNumber(c.getInt(c.getColumnIndex(KEY_MAXMEMBERS)));

		return td;
	}

	/*
	 * getting all groups
	 * */
	public List<Groups> getAllGroups() {
		List<Groups> groups = new ArrayList<Groups>();
		String selectQuery = "SELECT  * FROM " + TABLE_GROUP;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Groups td = new Groups();
				td.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
				td.setLocation((c.getString(c.getColumnIndex(KEY_LOCATION))));
				td.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
				td.setMaximumNumber(c.getInt(c.getColumnIndex(KEY_MAXMEMBERS)));

				// adding to todo list
				groups.add(td);
			} while (c.moveToNext());
		}

		return groups;
	}

	/*
	 * Updating a Group
	 */
	public int updateGroup(Groups group) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CODE, group.getCourseID());
		values.put(KEY_LOCATION, group.getLocation());
		values.put(KEY_DESCRIPTION, group.getDescription());
		values.put(KEY_MAXMEMBERS, group.getMaximumNumbers());

		// updating row
		return db.update(TABLE_GROUP, values, KEY_CODE + " = ?",
				new String[] { String.valueOf(group.getCourseID()) });
	}

	/*
	 * Deleting a Group
	 */
	public void deleteGroup(String groupid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GROUP, KEY_CODE + " = ?",
				new String[] { String.valueOf(groupid) });
	}
}