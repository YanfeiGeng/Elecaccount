package com.ivan.consume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ivan.consume.bean.ConsumeGroup;
import com.ivan.consume.bean.ConsumeRecord;
import com.ivan.util.DBHelper;
import com.ivan.util.DateUtil;

public class ConsumeDAO {
	
	private SQLiteDatabase db = null;

	public ConsumeDAO(Context context){
		DBHelper helper = new DBHelper(context);
		this.db =  helper.getWritableDatabase();
	}
	
	
	/**
	 * For the moment, we treat one day's consumption as one single group.
	 * It may contains following situations:
	 * 1. Single record
	 * 2. Multiple records during one time.
	 * 3. Multiple records during sever times. Like: morning buy sth, noon buy sth, evening buy sth and etc.
	 * We planned to only add date for now
	 * FIXME: Will implement time in the future. 
	 * @return
	 */
	public ConsumeGroup getConsumeGroupByDate(String date){
		if(date == null || "".endsWith(date)){
			return null;
		}
		ConsumeGroup today = null;
		Cursor cursor = db.query("consume_group", new String[]{"group_id", "group_name", "total_cost", "date"}, "date = '" + date + "'", null, null, null, null);
		if(!cursor.moveToFirst()){
			String INSERT_GROUP = "INSERT INTO consume_group(group_name, total_cost, date) VALUES(?, ?, ?)";
			SQLiteStatement state = this.db.compileStatement(INSERT_GROUP);
			state.bindString(1, DateUtil.getWeekNameInLocale(date));
			state.bindString(2, "0");
			state.bindString(3, date);
			state.executeInsert();
		}
		
		cursor = db.query("consume_group", new String[]{"group_id", "group_name", "total_cost", "date"}, "date = '" + date + "'", null, null, null, null);
		if(cursor.moveToFirst()){
			today = new ConsumeGroup();
			today.setGroupId(cursor.getString(0));
			today.setGroupName(cursor.getString(1));
			today.setTotalCost(cursor.getInt(2));
			today.setDate(cursor.getString(3));
			return today;
		}
		return null;
	}
	
	
	
	/**
	 * Get all consume groups 
	 */
	public Map<Integer, ConsumeGroup> getConsumeGroup(){
		Map<Integer, ConsumeGroup> groups = new HashMap<Integer, ConsumeGroup>();
		Cursor dbGroups = db.query("consume_group", new String[]{"group_id", "group_name", "total_cost", "date"}, null, null, null, null, "date desc");
		if(dbGroups.moveToFirst()){
			do{
				String groupId = dbGroups.getString(0),
					groupName = dbGroups.getString(1),
					groupDate = dbGroups.getString(3);
				int totalCost = dbGroups.getInt(2);
				ConsumeGroup oneGroup = new ConsumeGroup();
				oneGroup.setGroupId(groupId);
				oneGroup.setGroupName(groupName);
				oneGroup.setTotalCost(totalCost);
				oneGroup.setDate(groupDate);
				groups.put(new Integer(groupId), oneGroup);
			}while(dbGroups.moveToNext());
		}
		return groups;
	} 
	
	
	/**
	 * Combine all records to the groups
	 * @return
	 */
	public Map<ConsumeGroup, List<ConsumeRecord>> getConsumeRecords(){
		Map<ConsumeGroup, List<ConsumeRecord>> records = new HashMap<ConsumeGroup, List<ConsumeRecord>>();
		Cursor dbRecords = db.query("consume_record", new String[]{"consume_id, consume_name, consume_cate_id, consume_group_id, consume_date, price, total, comments"}, 
				null, null, null, null, "consume_date desc");
		Map<Integer, ConsumeGroup> groups = this.getConsumeGroup();
		if(dbRecords.moveToFirst()){
			do{
				//consume_id, consume_name, consume_cate_id, consume_group_id, consume_date, price, total, comments
				String consume_id = dbRecords.getString(0),
					consume_name = dbRecords.getString(1), consume_date = dbRecords.getString(4),
					comments = dbRecords.getString(7);
				int consume_cate_id = dbRecords.getInt(2), consume_group_id = dbRecords.getInt(3),
					price = dbRecords.getInt(5), total = dbRecords.getInt(6);
				
				ConsumeRecord consumeRecord = new ConsumeRecord();
				consumeRecord.setConsume_id(consume_id);
				consumeRecord.setConsume_name(consume_name);
				consumeRecord.setConsume_cate_id(consume_cate_id);
				consumeRecord.setConsume_group_id(consume_group_id);
				consumeRecord.setConsume_date(consume_date);
				consumeRecord.setPrice(price);
				consumeRecord.setTotal(total);
				consumeRecord.setComments(comments);
				
				//Get group first
				ConsumeGroup group = groups.get(consume_group_id);
				List<ConsumeRecord> consumeRecords = records.get(group);
				boolean isIncluded = true;
				if(consumeRecords == null){
					consumeRecords = new ArrayList<ConsumeRecord>();
					isIncluded = false;
				}
				consumeRecords.add(consumeRecord);
				if(!isIncluded){
					records.put(group, consumeRecords);
				}
			}while(dbRecords.moveToNext());
		}
		return records;
	}
	
}
