package com.ivan.consume;

import java.util.List;

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

public class ConsumeRecordList extends ExpandableListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ExpandableListAdapter crAdpter = new ConsumptionRecordsListAdapter();
		this.setListAdapter(crAdpter);
	}
	
//	private String[] consumeGroups = {"星期一", "星期二", "星期三", "星期四"};
	private List<ConsumeGroup> consumeGroups;
	
	private String[][] consumeRecords = {
			{"1.1", "1.2", "1.3", "1.4", "1.5"},
			{"2.1", "2.2", "2.3", "2.3"},
			{"3.1", "3.2", "3.3", "3.4", "3.5", "3.6"},
			{"4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7"}
		};
	
	private ConsumeDAO consumeDAO = new ConsumeDAO(ConsumeRecordList.this);

	/**
	 * Initialize group 
	 */
	private void initGroupsAndRecords(){
		//Init the group names
		consumeGroups = consumeDAO.getConsumeGroup();
		consumeRecords = consume
		
	}
	               
	               
	
	public class ConsumptionRecordsListAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return consumeRecords[groupPosition][childPosition];
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
			return consumeRecords[groupPosition].length;
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
