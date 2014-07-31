package com.little.produce.qrcode.util;

public class UtilString {
	
	public static boolean isEmpty(String string){
		
		boolean flag = true;
		
		if(string != null && !"".equals(string)){
			
			flag = false;
			
		}
		
		return flag;
		
	}

}
