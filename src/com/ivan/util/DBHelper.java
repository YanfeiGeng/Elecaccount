package com.ivan.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "elecaccount.db";
	
	private String CREATE_CONSUME_RECORD = "CREATE TABLE CONSUME_RECORD (consume_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			" consume_name VARCHAR, consume_cate INTEGER, consume_group_id INTEGER, consume_date DATE, price INTEGER, total INTEGER, comments VARCHAR)";
	
	private String CREATE_CONSUME_GROUP = "CREATE TABLE CONSUME_GROUP (group_id INTEGER, group_name VARCHAR, total_cost INTEGER, date DATE)";
	
	private String CREATE_CONSUME_CATE = "CREATE TABLE CONSUME_CATE (cate_id INTEGER, cate_name VARCHAR, other VARCHAR)"; 

	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.CREATE_CONSUME_RECORD);
		db.execSQL(this.CREATE_CONSUME_GROUP);
		db.execSQL(this.CREATE_CONSUME_CATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
