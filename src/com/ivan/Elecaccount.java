package com.ivan;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.ivan.consume.AddConsumeRecord;
import com.ivan.consume.ConsumeRecordList;
import com.ivan.util.EC;

public class Elecaccount extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        		
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.main, tabHost.getTabContentView(), true);
        
        tabHost.addTab(tabHost.newTabSpec("consuRecords")
        		.setIndicator("消费记录")
        		.setContent(new Intent(this, ConsumeRecordList.class)));
        tabHost.addTab(tabHost.newTabSpec("cateManage")
        		.setIndicator("类别管理")
        		.setContent(R.id.secondTabView));
        tabHost.addTab(tabHost.newTabSpec("recAnalyze")
        		.setIndicator("记录分析")
        		.setContent(R.id.thirdTabView));
        
        //Trigger the menu to re-added after the tab has been changed
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){

			@Override
			public void onTabChanged(String arg0) {
				System.out.println("Tab has been changed to :" + getTabHost().getCurrentTab());
				if(null != Elecaccount.this.baseMenu){
					baseMenu.clear();
					Elecaccount.this.onCreateOptionsMenu(Elecaccount.this.baseMenu);
				}
			}
        });
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int selectedMenu = item.getItemId();
		
		//Switch on all menuItems, then trigger different operation
		switch(selectedMenu){
			case EC.ADD_CONSUME_RECORD_MENU:
				Toast.makeText(Elecaccount.this, "准备添加消费记录...", Toast.LENGTH_SHORT);
				
				//Open add consume record form
				Intent addConsumeRecordForm = new Intent();
				addConsumeRecordForm.setClass(Elecaccount.this, AddConsumeRecord.class);
				Elecaccount.this.startActivity(addConsumeRecordForm);
				
				break;
			case EC.DEL_CONSUME_RECORD_MENU:
				break;
			case EC.ADD_CATEGORY_MENU:
				break;
			case EC.DEL_CATEGORY_MENU:
				break;
			case EC.EXT_MENU:
				break;
			case EC.ABOUT_MENU:
				break;
			default:
				break;
		}
		
//		tabSelected();
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Return current selected Tab
	 * @return
	 */
	public int tabSelected(){
		int SELECTED_TAB = -1;
		TabHost tabHost = getTabHost();
		if(tabHost != null){
			SELECTED_TAB = tabHost.getCurrentTab();
		}
		return SELECTED_TAB;
	}

	//Hold the Menu object to add different widgets to different tabs
	private Menu baseMenu = null;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.baseMenu = menu;
		int CURRENT_TAB = this.tabSelected();
		switch(CURRENT_TAB){
			case 0:
				menu.addSubMenu(0, EC.ADD_CONSUME_RECORD_MENU, EC.ADD_CONSUME_RECORD_MENU, R.string.add_consume_record);
				menu.addSubMenu(0, EC.DEL_CONSUME_RECORD_MENU, EC.DEL_CONSUME_RECORD_MENU, R.string.del_consume_record);
				menu.addSubMenu(0, EC.ABOUT_MENU, EC.ABOUT_MENU, R.string.about);
				menu.addSubMenu(0, EC.EXT_MENU, EC.EXT_MENU, R.string.exit);
				break;
			case 1:
				menu.addSubMenu(0, EC.ADD_CATEGORY_MENU, EC.ADD_CATEGORY_MENU, R.string.add_category);
				menu.addSubMenu(0, EC.DEL_CATEGORY_MENU, EC.DEL_CATEGORY_MENU, R.string.del_category);
				menu.addSubMenu(0, EC.ABOUT_MENU, EC.ABOUT_MENU, R.string.about);
				menu.addSubMenu(0, EC.EXT_MENU, EC.EXT_MENU, R.string.exit);
				break;
			case 2:
				menu.addSubMenu(0, EC.ABOUT_MENU, EC.ABOUT_MENU, R.string.about);
				menu.addSubMenu(0, EC.EXT_MENU, EC.EXT_MENU, R.string.exit);
				break;
			default: 
				break;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
}