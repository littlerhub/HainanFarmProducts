package com.little.produce.qrcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.little.produce.qrcode.*;
import com.little.produce.qrcode.action.ServiceQRCode;
import com.little.produce.qrcode.dao.SDCard;

public class ActivityQRCode extends Activity {
	
	private ImageView mImageView = null;
	private String content = null;
	private Bitmap bmp = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrcode);
		mImageView = (ImageView)findViewById(R.id.mImageView);
		
		//---ȡ�ô���������
		Intent intent = this.getIntent();
		content = intent.getStringExtra("content");
		
		try {
						
			if(content != null && !"".equals(content)){
				//---���ɶ�ά��
				bmp = ServiceQRCode.create(content, ActivityQRCode.this);
				if(bmp != null){
					mImageView.setImageBitmap(bmp);				
				}			
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	//---���������ط�ʹActivity��ʧ
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}

	//---��������桱��ť
	public void onButtonSaveClick(View v) {  
		
		try {
			
			if(bmp != null){

				String result = SDCard.save(bmp, content);
				if(result != null){
										
					 AlertDialog.Builder builder = new AlertDialog.Builder(ActivityQRCode.this);   
				        builder.setTitle(ActivityQRCode.this.getResources().getString(R.string.title_dialog_save));  
				        builder.setMessage(result);  
				        builder.setPositiveButton(ActivityQRCode.this.getResources().getString(R.string.btn_negative),  
				                new DialogInterface.OnClickListener() {  
				                    public void onClick(DialogInterface dialog, int whichButton) {  
				                        
				                    }  
				                });  
				        builder.show().show();
					
				}else{
					
					Toast.makeText(ActivityQRCode.this, "�޷�ʶ��SD��!", Toast.LENGTH_SHORT).show();
					
				}
			}
					
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	
    }  
	
}
