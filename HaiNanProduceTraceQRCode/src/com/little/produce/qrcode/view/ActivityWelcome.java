package com.little.produce.qrcode.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.little.produce.qrcode.*;
/**
 * ����Ļ�ӭ����.
 * @author LittleBoy
 *
 */
public class ActivityWelcome extends Activity{
	
	//�ӳ�2.5�� 
	private final long DISPLAY_DURATION = 2500; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        new Handler().postDelayed(new Runnable(){   
                
            public void run() {   
            	
                Intent intent = new Intent(ActivityWelcome.this, ActivityMain.class);   
                ActivityWelcome.this.startActivity(intent);  
                ActivityWelcome.this.overridePendingTransition(R.anim.activity_enter_one, R.anim.activity_enter_two);
                ActivityWelcome.this.finish();   
                
            }   
                 
           }, DISPLAY_DURATION);   
		
    }
}
