package com.little.produce.qrcode.view;

import com.little.produce.qrcode.R;
import com.little.produce.qrcode.action.ServiceEditTextDate;
import com.little.produce.qrcode.action.ServiceEditTextID;
import com.little.produce.qrcode.action.ServiceEditTextWebsite;
import com.little.produce.qrcode.util.UtilKeyboard;
import com.little.produce.qrcode.util.UtilString;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
public class ActivityMain extends Activity {

	private EditText mID = null;
	private EditText mSource = null;
	private EditText mName = null;
	private EditText mProduceDate = null;
	private EditText mPackageDate = null;
	private EditText mWebsite = null;
	private EditText mList = null;
	private EditText mAddress = null;
	
	private String[] defContent = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        
        addListeners();
        
    }

	private void addListeners() {
		
		mID.addTextChangedListener(new ServiceEditTextID(this));
		mID.setOnFocusChangeListener(new ServiceEditTextID(this));
		mWebsite.setOnFocusChangeListener(new ServiceEditTextWebsite(this));
		mWebsite.addTextChangedListener(new ServiceEditTextWebsite(this));
		mList.setOnFocusChangeListener(new ServiceEditTextWebsite(this));
		mList.addTextChangedListener(new ServiceEditTextWebsite(this));
		mAddress.setOnFocusChangeListener(new ServiceEditTextWebsite(this));
		mAddress.addTextChangedListener(new ServiceEditTextWebsite(this));
		mProduceDate.setOnFocusChangeListener(new ServiceEditTextDate(this));
		mProduceDate.setOnClickListener(new ServiceEditTextDate(this));
		mPackageDate.setOnFocusChangeListener(new ServiceEditTextDate(this));
		mPackageDate.setOnClickListener(new ServiceEditTextDate(this));
		
	}

	private void init() {
		
		mID = (EditText)findViewById(R.id.mID);
		mSource = (EditText)findViewById(R.id.mSource);
		mName = (EditText)findViewById(R.id.mName);
		mProduceDate = (EditText)findViewById(R.id.mProduceDate);
		mPackageDate = (EditText)findViewById(R.id.mPackageDate);
		mWebsite = (EditText)findViewById(R.id.mWebsite);
		mList = (EditText)findViewById(R.id.mList);
		mAddress = (EditText)findViewById(R.id.mAddress);
		
		defContent = new String[]{this.getResources().getString(R.string.produce_id), 
				this.getResources().getString(R.string.produce_source), 
				this.getResources().getString(R.string.produce_name), 
				this.getResources().getString(R.string.produce_date), 
				this.getResources().getString(R.string.produce_package_date),
				this.getResources().getString(R.string.produce_website),
				this.getResources().getString(R.string.produce_list),
				this.getResources().getString(R.string.produce_address)};
		
		mID.setInputType(DEFAULT_KEYS_DISABLE);
		mProduceDate.setInputType(DEFAULT_KEYS_DISABLE);
		mPackageDate.setInputType(DEFAULT_KEYS_DISABLE);
		
	}

	public void onButtonQRCreateClick(View v){
		
		StringBuilder sb = new StringBuilder();		
		boolean allFlag = false;
		boolean fiveFlag = false;
		//---ȡ�ø�EditText�е�����
		String id = mID.getText().toString();
		String source = mSource.getText().toString();
		String name = mName.getText().toString();
		String produceDate = mProduceDate.getText().toString();
		String packageDate = mPackageDate.getText().toString();
		String website = mWebsite.getText().toString();
		String list = mList.getText().toString();
		String address = mAddress.getText().toString();
		
		String[] content = new String[]{id, source, name, produceDate, packageDate, website, list, address};
		
		
		for(int i = 0; i < content.length; i++){
					
			if(!UtilString.isEmpty(content[i])){
				allFlag = true;
				if(i < 5){
					fiveFlag = true;
				}
				break;
			}
					
		}
		//---"���ݲ���Ϊ�գ�"���û�ɶ��û���ֱ�ӵ����ɰ�ť��
		if(allFlag == false){
			Toast.makeText(ActivityMain.this, 
					this.getResources().getString(R.string.toast_empty), 
					Toast.LENGTH_SHORT).show();
			return;
		}
		//---"ǰ5��ĿΪ�������ݣ�"(���ǰ5��û����Ǻ�3���������)
		if(fiveFlag == false && allFlag == true){
			Toast.makeText(getApplicationContext(), 
					this.getResources().getString(R.string.toast_empty_five), 
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		//---"ID���ݱ���Ϊ14λ"(���ID���ݲ���14λ�Ļ���������ʾ������)
		if(!UtilString.isEmpty(id) && (id.trim()).length() != 14){
		
			Toast.makeText(getApplicationContext(), 
					this.getResources().getString(R.string.toast_edit_id), 
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		//---"��"ĳ"���Ϊ�գ�"(ǰ5��Ϊ�������ݣ������û��ģ���˳�������ʾ)
		for(int i = 0; i < 5; i++){
			if(UtilString.isEmpty(content[i])){
				
				Toast.makeText(getApplicationContext(), 
						this.getResources().getString(R.string.toast_empty_item_pre) + 
						(++i) + 
						this.getResources().getString(R.string.toast_empty_item) + 
						this.getResources().getString(R.string.toast_empty_five), 
						Toast.LENGTH_SHORT).show();
				return;
				
			}
		}
		
		
		System.out.println(sb.toString());
		//---ʹ�������ʧ
		UtilKeyboard.dismissSoftInput(mAddress, ActivityMain.this);
				
		//---����ÿ��EditText��ǰ׺
		for(int i = 0; i < content.length; i++){
								
			content[i] = defContent[i] + content[i];
			sb.append("\n" + content[i]);
				
		}
					
		String contents = sb.toString();
		Intent intent = new Intent(ActivityMain.this, ActivityQRCode.class);
		intent.putExtra("content", contents);
		this.startActivity(intent);
		
	}
	
	public void onIDClick(View v){

		if(((EditText)v).getInputType() == DEFAULT_KEYS_DISABLE){
			((EditText)v).setInputType(BIND_AUTO_CREATE);
			InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		   	imm.toggleSoftInput(BIND_AUTO_CREATE, InputMethodManager.HIDE_NOT_ALWAYS);
		}
		
	}
	
}
