package com.little.produce.qrcode.action;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class ServiceEditTextDate implements OnFocusChangeListener, OnClickListener{

	private Context context = null;
	private DatePickerDialog mDateDialog = null;
	private EditText mEditText = null;
	
	public ServiceEditTextDate(Context context) {
		
		this.context = context;
		
	}

	public void onFocusChange(View arg0, boolean arg1) {
		
		if(arg1){
						
			showDateSelectorDialog(arg0);
			
		}

	}

	private void showDateSelectorDialog(View view) {
		
		mEditText = (EditText)view;
		//---取得当前日期
		Calendar mCalendar = Calendar.getInstance();
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("===============" + month);
		//---设置日期Dialog，并显示
		mDateDialog = new DatePickerDialog(context, new OnMyDateSetListener(), year, month, day);
		mDateDialog.show();
		
	}

	class OnMyDateSetListener implements OnDateSetListener {

		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			String year = String.valueOf(arg1);
			String month = "";
			String day = "";
			//System.out.println("年：" + arg1 + " 月：" + arg2 + " 日：" + arg3);
			if(++arg2 < 10){
				month = "0" + arg2;
			}else{
				month = String.valueOf(arg2);
			}
			
			if(arg3 < 10){
				day = "0" + arg3;
			}else{
				day = String.valueOf(arg3);
			}
			//System.out.println("年：" + year + " 月：" + month + " 日：" + day);
			mEditText.setText(year + month + day);
			mEditText.setSelection(mEditText.getText().length());
			
		}
		
	}

	public void onClick(View arg0) {

		showDateSelectorDialog(arg0);
		
	}

}
