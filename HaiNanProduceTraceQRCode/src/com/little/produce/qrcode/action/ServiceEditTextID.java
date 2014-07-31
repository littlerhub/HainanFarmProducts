package com.little.produce.qrcode.action;

import com.little.produce.qrcode.R;
import com.little.produce.qrcode.util.UtilString;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceEditTextID implements TextWatcher, OnFocusChangeListener {

	private Context context;
	private int count = 0;
	
	public ServiceEditTextID(Context context){
		
		this.context = context;
		
	}
	
	public void afterTextChanged(Editable arg0) {
		
		//---输入的字数不能超过18位
		if(arg0.length() > 14){
			//---显示:【物品编码】不能超过14位
			Toast.makeText(context, context.getResources().getString(R.string.toast_edit_id), Toast.LENGTH_SHORT).show();
			arg0.delete(14, arg0.length());		

		}
		
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		
		

	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		
		

	}

	public void onFocusChange(View arg0, boolean arg1) {
		if(arg1){
			count++;
		}else{
			//---当ID输入框失去焦点，并且内容不为空的时候,给出提示
			if(!UtilString.isEmpty(((EditText)arg0).getText().toString()) && ((EditText)arg0).length() < 14){
				Toast.makeText(context, context.getResources().getString(R.string.toast_edit_id), 
						Toast.LENGTH_SHORT).show();
			}
			
		}
		if(count >=2 && ((EditText)arg0).getInputType() == Activity.DEFAULT_KEYS_DISABLE){
			
			((EditText)arg0).setInputType(Context.BIND_AUTO_CREATE);
			InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		   	imm.toggleSoftInput(Context.BIND_AUTO_CREATE, InputMethodManager.HIDE_NOT_ALWAYS);
		   	
		}
		
	}

}
