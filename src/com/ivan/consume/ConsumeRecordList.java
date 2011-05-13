package com.ivan.consume;

import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.ivan.consume.bean.ConsumeGroup;
import com.ivan.consume.bean.ConsumeRecord;

public class ConsumeRecordList extends ExpandableListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		consumeDAO = new ConsumeDAO(ConsumeRecordList.this);
		
		//Init groups and records
		initGroupsAndRecords();
		
		ExpandableListAdapter crAdpter = new ConsumptionRecordsListAdapter();
		this.setListAdapter(crAdpter);
	}
	
	private ConsumeGroup[] consumeGroups;
	
	private Map<ConsumeGroup, List<ConsumeRecord>> consumeRecords;
	
	private ConsumeDAO consumeDAO;

	/**
	 * Initialize group 
	 */
	private void initGroupsAndRecords(){
		//Init the group names
		Object[] initGroups = consumeDAO.getConsumeGroup().values().toArray();
		consumeGroups = new ConsumeGroup[initGroups.length];
		for(int i = 0; i < initGroups.length; i++){
			consumeGroups[i] = (ConsumeGroup) initGroups[i];
		}
		consumeRecords = consumeDAO.getConsumeRecords();
	}
	               
	               
	
	public class ConsumptionRecordsListAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			ConsumeGroup group = consumeGroups[groupPosition];
			List<ConsumeRecord> subRecords = consumeRecords.get(group);
			return subRecords.get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		
		public TextView generateGenericView(){
			TextView textView = new TextView(ConsumeRecordList.this);
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
			
			textView.setLayoutParams(params);
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			textView.setPadding(36, 0, 0, 0);
			return textView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView childView = this.generateGenericView();
			childView.setText(this.getChild(groupPosition, childPosition).toString());
			return childView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			ConsumeGroup group = consumeGroups[groupPosition];
			List<ConsumeRecord> subRecords = consumeRecords.get(group);
			return subRecords.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return consumeGroups[groupPosition];
		}

		@Override
		public int getGroupCount() {
			return consumeGroups.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView groupView = this.generateGenericView();
			
			groupView.setText(this.getGroup(groupPosition).toString());
			return groupView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}
		
	}
	
	
}
