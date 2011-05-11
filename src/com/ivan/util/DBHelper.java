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
	
	private static final String DB_NAME = "elecaccount.db";
	
	private String CREATE_CONSUME_RECORD = "CREATE TABLE CONSUME_RECORD (consume_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			" consume_name VARCHAR, consume_cate INTEGER, consume_date DATE, price INTEGER, quntity INTEGER, comments VARCHAR)";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.CREATE_CONSUME_RECORD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
