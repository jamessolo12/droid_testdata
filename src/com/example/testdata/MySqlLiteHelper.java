package com.example.testdata;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME		= "muppets.db";
	private static final int	DATABASE_VERSION	= 1;
	private static final String	TABLE_NAME			= "theusers";

	// Database creation sql statement
	private static final String DATABASE_CREATE		= "create table " + TABLE_NAME + " (userid integer primary key autoincrement, muppetname text not null);";
	private static final String DATABASE_ADD_USER	= "insert into "  + TABLE_NAME + " (muppetname) values (";
  
	
	public MySqlLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySqlLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	
	public void addNewuser(SQLiteDatabase database, String name){
		String insertString	= DATABASE_ADD_USER + "'" + name + "');";
		Log.e("blug",insertString);
		database.execSQL(insertString);
	}
	
	
	public void addNewUserProperly(SQLiteDatabase database, String name){
	    ContentValues values	= new ContentValues();
	    values.put("muppetname", name);
	    database.insert(TABLE_NAME, null, values);
	}
	
	
	public List<Muppet> getAllUsers(SQLiteDatabase database){
		List<Muppet> muppets = new ArrayList<Muppet>();
	    String[] columns	= {"userid","muppetname"};

	    Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);

	    cursor.moveToLast();
	    while (!cursor.isBeforeFirst()) {
	    	Muppet muppet = cursorToMuppet(cursor);
	    	muppets.add(muppet);
	    	cursor.moveToPrevious();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return muppets;
	}
	
	
	private Muppet cursorToMuppet(Cursor cursor) {
		Muppet muppet = new Muppet();
		muppet.setMuppetId(cursor.getInt(0));
		muppet.setMuppetName(cursor.getString(1));
		return muppet;
	}
	
} 