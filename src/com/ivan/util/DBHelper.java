package com.ivan.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * 
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private String CREATE_CONSUME_RECORD = "CREATE TABLE CONSUME_RECORD (consume_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			" consume_name VARCHAR, consume_date DATE, price INTEGER, quntity INTEGER, comments VARCHAR)";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.CREATE_CONSUME_RECORD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
