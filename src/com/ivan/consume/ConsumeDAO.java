package com.ivan.consume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ivan.consume.bean.ConsumeGroup;
import com.ivan.consume.bean.ConsumeRecord;
import com.ivan.util.DBHelper;

public class ConsumeDAO {
	
	private SQLiteDatabase db = null;

	public ConsumeDAO(Context context){
		this.db = new DBHelper(context).getWritableDatabase();
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
	 * 
	 * @return
	 */
	public List<Map<ConsumeGroup, ConsumeRecord>> getConsumeRecords(){
		List<Map<ConsumeGroup, ConsumeRecord>> records = new ArrayList<Map<ConsumeGroup, ConsumeRecord>>();
		Cursor dbRecords = db.query("consume_record", new String[]{"consume_id, consume_name, consume_cate, consume_group_id, consume_date, price, total, comments"}, 
				null, null, null, null, "consume_date desc");
		Map<Integer, ConsumeGroup> groups = this.getConsumeGroup();
		if(dbRecords.moveToFirst()){
			do{
				
//				quntity -> total
//				+ consume_group_id
				
				
			}while(dbRecords.moveToNext());
		}
		
		return records;
	}
	
}
