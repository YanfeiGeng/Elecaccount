package com.ivan.consume;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ivan.Elecaccount;
import com.ivan.R;
import com.ivan.consume.bean.ConsumeRecord;
import com.ivan.util.DBHelper;

public class AddConsumeRecord extends Activity{
	
	//Define all widgets
	private EditText consumeName;
	
	private Spinner consumeCate;
	
	private String[] cates = {"食品", "衣服鞋子", "生活用品", "娱乐休闲", "工作学习", "其他"};;
	
	private DatePicker consumeDate;
	
	private EditText consumePrice;
	
	private EditText consumeQuntity;
	
	private EditText consumeComments;
	
	private Button addConsumeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.add_consume_record);
		
		initFields();
		
		initConsuCate();
		
		//Listener for clicking save button
		OnClickListener ocl = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				String name = AddConsumeRecord.this.consumeName.getText().toString();
				String category = AddConsumeRecord.this.consumeCate.getSelectedItem().toString();
				long cate_A = AddConsumeRecord.this.consumeCate.getSelectedItemId();
				String date = AddConsumeRecord.this.consumeDate.getYear() + "-" + (AddConsumeRecord.this.consumeDate.getMonth()+1) + "-" + AddConsumeRecord.this.consumeDate.getDayOfMonth();
				String price = AddConsumeRecord.this.consumePrice.getText().toString();
				String quntity = AddConsumeRecord.this.consumeQuntity.getText().toString();
				String comments = AddConsumeRecord.this.consumeComments.getText().toString();
				Object[][] values = {{name, cate_A, date, price, quntity}, 
							{getResources().getString(R.string.add_conlabel_name), getResources().getString(R.string.add_conlabel_cate), getResources().getString(R.string.add_conlabel_date),
							getResources().getString(R.string.add_conlabel_price), getResources().getString(R.string.add_conlabel_quntity)}};
				boolean isValidate = true;
				for(int i = 0; i < values[0].length; i++){
					System.out.println("Outer: Come in....." + values[1][i]);
					if(values[0][i] == null || "".equals(values[0][i].toString())) {
						isValidate = false;
						System.out.println("Come in....." + values[1][i] + ", " + values[0][i]);
						AddConsumeRecord.this.showMsg("Field: " + ((String)values[1][i]).replace("：", "") + " can not be empty!");
						break;
					}
				}
				
				if(isValidate){
					//Do sth...
					String INSERT_SQL = "INSERT INTO CONSUME_RECORD(consume_name, consume_cate, consume_date, price, quntity, comments) VALUES(?, ?, ?, ?, ?, ?)";
					SQLiteDatabase db = new DBHelper(AddConsumeRecord.this).getWritableDatabase();
					SQLiteStatement state = db.compileStatement(INSERT_SQL);
					state.bindString(1, name);
					state.bindString(2, category);
					state.bindString(3, date);
					state.bindString(4, price);
					state.bindString(5, quntity);
					state.bindString(6, comments);
					state.executeInsert();
					
					List<ConsumeRecord> records = new ArrayList<ConsumeRecord>();
					Cursor cursor = db.query("CONSUME_RECORD", new String[]{"consume_name", "consume_cate", "consume_date", "price", "quntity", "comments"}, null, null, null, null, "consume_date desc");
					System.out.println("Record Count#:" + cursor.getCount());
					if(cursor.moveToFirst()){
						do{
							ConsumeRecord oneRecord = new ConsumeRecord();
							oneRecord.setConsume_name(cursor.getString(0));
							oneRecord.setConsume_category(cursor.getString(1));
							oneRecord.setConsume_date(cursor.getString(2));
							oneRecord.setConsume_price(cursor.getString(3));
							oneRecord.setConsume_quntity(cursor.getString(4));
							oneRecord.setConsume_comments(cursor.getString(5));
							records.add(oneRecord);
						}while(cursor.moveToNext());
					}
					
					db.close();
					
					for(ConsumeRecord rec : records){
						System.out.println(rec);
					}
					
					Intent listView = new Intent();
//					listView.setClass(AddConsumeRecord.class, Elecaccount.this);
				}
			}
			
		};
		
		this.addConsumeBtn.setOnClickListener(ocl);
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * Initialize all fields to local var
	 */
	public void initFields(){
		//Initialize all fields
		this.setConsumeName((EditText) findViewById(R.id.add_consume_name));
		this.setConsumeCate((Spinner) findViewById(R.id.add_consume_cate));
		this.setConsumeDate((DatePicker) findViewById(R.id.add_consume_datetime));
		this.setConsumePrice((EditText) findViewById(R.id.add_consume_price));
		this.setConsumeQuntity((EditText) findViewById(R.id.add_consume_quntity));
		this.setConsumeComments((EditText) findViewById(R.id.add_consume_comments));
		this.setAddConsumeBtn((Button) findViewById(R.id.add_consume_btn));
	}
	
	/**
	 * Initialize consume category drop down list.
	 */
	public void initConsuCate(){
		List<String> catesValues = new ArrayList<String>();
		for(int i = 0; i < cates.length; i++){
			catesValues.add(cates[i]);
		}
		
		ArrayAdapter<String> categories = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item , catesValues);
		categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.consumeCate.setAdapter(categories);
	}
	
	
	/**
	 * Used for debug
	 */
	private void showMsg(String msg){
		Toast.makeText(AddConsumeRecord.this, msg, Toast.LENGTH_SHORT).show();
	}
	

	public EditText getConsumeName() {
		return consumeName;
	}

	public void setConsumeName(EditText consumeName) {
		this.consumeName = consumeName;
	}

	public Spinner getConsumeCate() {
		return consumeCate;
	}

	public void setConsumeCate(Spinner consumeCate) {
		this.consumeCate = consumeCate;
	}

	public DatePicker getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(DatePicker consumeDate) {
		this.consumeDate = consumeDate;
	}

	public EditText getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(EditText consumePrice) {
		this.consumePrice = consumePrice;
	}

	public EditText getConsumeQuntity() {
		return consumeQuntity;
	}

	public void setConsumeQuntity(EditText consumeQuntity) {
		this.consumeQuntity = consumeQuntity;
	}

	public EditText getConsumeComments() {
		return consumeComments;
	}

	public void setConsumeComments(EditText consumeComments) {
		this.consumeComments = consumeComments;
	}

	public Button getAddConsumeBtn() {
		return addConsumeBtn;
	}

	public void setAddConsumeBtn(Button addConsumeBtn) {
		this.addConsumeBtn = addConsumeBtn;
	}
	
}
