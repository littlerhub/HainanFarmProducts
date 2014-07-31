package com.little.produce.qrcode.action;

import com.little.produce.qrcode.util.UtilString;
import com.little.produce.qrcode.util.UtilURL;
import com.little.produce.qrcode.*;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceEditTextWebsite implements OnFocusChangeListener , TextWatcher{

	private Context context;
	private static EditText mEditText;
	private static String previousUrl;
	private static int start;
	private static int end;
	
	public ServiceEditTextWebsite(Context context) {
		
		this.context = context;
		
	}

	public void onFocusChange(View arg0, boolean arg1) {
		mEditText = ((EditText)arg0);
		//---获取焦点时
		if(arg1){
			
			if(UtilString.isEmpty(((EditText)arg0).getText().toString())){
				
				((EditText)arg0).setText(context.getResources().getString(R.string.text_edit_def_website));
				//---将光标置于最后
				((EditText)arg0).setSelection((("http://www.").trim()).length());
				
			}
			
		//---失去焦点时
		}else{
			
			if(context.getResources().getString(R.string.text_edit_def_website).equals(
					((EditText)arg0).getText().toString())){
				
				((EditText)arg0).setText("");
				
			}
			
		}

	}

	public void afterTextChanged(Editable arg0) {
		
		String url = mEditText.getText().toString();
		System.out.println("===============afterTextChanged============" + url);
		end = mEditText.getSelectionStart();	
		System.out.println("++++++++++++++++++++++++" + start);
		if(!context.getResources().getString(R.string.text_edit_def_website).equals(url)){			
			
			if(!UtilString.isEmpty(url) && !UtilURL.isURL(url)){
				
				Toast.makeText(context, context.getResources().getString(R.string.toast_website_error), 
						Toast.LENGTH_SHORT).show();	
				//arg0.delete(start, end);	
			}
			
		}
		
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		System.out.println(arg0.toString() + "===============");
		if("".equals(arg0.toString())){
			previousUrl = context.getResources().getString(R.string.text_edit_def_website);
			start = 11;
		}else{
			previousUrl = arg0.toString();
			start = mEditText.getSelectionStart();
		}
		System.out.println("++++++++++++++++++++++++" + start);
		System.out.println("===============beforeTextChanged============" + previousUrl);
		
	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		

	}

}
