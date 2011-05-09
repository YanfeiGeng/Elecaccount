package com.ivan.consume;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ivan.R;

public class AddConsumeRecord extends Activity{
	
	//Define all widgets
	private EditText consumeName;
	
	private Spinner consumeCate;
	
	private DatePicker consumeDate;
	
	private EditText consumePrice;
	
	private EditText consumeQuntity;
	
	private EditText consumeComments;
	
	private Button addConsumeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.add_consume_record);
		
		//Initialize all fields
		this.setConsumeName((EditText) findViewById(R.id.add_consume_name));
		this.setConsumeDate((DatePicker) findViewById(R.id.add_consume_datetime));
		this.setConsumePrice((EditText) findViewById(R.id.add_consume_price));
		this.setConsumeQuntity((EditText) findViewById(R.id.add_consume_quntity));
		this.setConsumeComments((EditText) findViewById(R.id.add_consume_comments));
		this.setAddConsumeBtn((Button) findViewById(R.id.add_consume_btn));
		
		//Listener for clicking save button
		OnClickListener ocl = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumeName.getText().toString());
				System.out.println(AddConsumeRecord.this.consumeName.getText().toString());
//				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumeCate.getSelectedItem() + ", " + AddConsumeRecord.this.consumeCate.getSelectedItemId());
//				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumeDate.getYear() + "-" + AddConsumeRecord.this.consumeDate.getMonth() + "-" + AddConsumeRecord.this.consumeDate.getDayOfMonth());
//				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumePrice.getText().toString());
//				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumeQuntity.getText().toString());
//				AddConsumeRecord.this.showMsg(AddConsumeRecord.this.consumeComments.getText().toString());
			}
			
		};
		
		this.addConsumeBtn.setOnClickListener(ocl);
		super.onCreate(savedInstanceState);
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
