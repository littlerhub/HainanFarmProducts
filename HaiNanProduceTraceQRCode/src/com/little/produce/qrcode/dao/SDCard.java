package com.little.produce.qrcode.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import android.graphics.Bitmap;
import android.os.Environment;

public class SDCard {
		//---�����ά�뵽SD��
		public static String save(Bitmap qrBmp, String qrStr) throws IOException {  
			 //�����ж�SD������
			 if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
				 
				 return null;
				 
			 }else{
				 long fileNamePath = new Date().getTime();
				 //����Ŀ¼
				 File fileDir = new File(Environment.getExternalStorageDirectory() , "/LittleQRCode/ScreenShot/" + fileNamePath +"/");
				 if(!fileDir.exists()){
					 fileDir.mkdirs();
				 }
				 //�����ļ�			 
				 File pngFile = new File(fileDir.getAbsolutePath() + File.separator + fileNamePath + ".PNG");
				 File txtFile = new File(fileDir.getAbsolutePath() + File.separator + fileNamePath + ".TXT");
				 if(!pngFile.exists()){
					 pngFile.createNewFile();
				 }
				 if(!txtFile.exists()){
					 txtFile.createNewFile();
				 }
				 BufferedOutputStream bosPNG = new BufferedOutputStream(new FileOutputStream(pngFile)); 
				 PrintWriter bosTXT = new PrintWriter(new FileWriter(txtFile));
				 bosTXT.write(qrStr);
				 bosTXT.close();
				 qrBmp.compress(Bitmap.CompressFormat.PNG, 80, bosPNG);  
				 bosPNG.flush();  
				 bosPNG.close(); 
				 
				 return fileDir.getPath() + fileNamePath;
			 }
		        	         
		 }
}
