package com.little.produce.qrcode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilURL {

	public static boolean isURL(String url) {
		
		boolean flag = false;
		
		Pattern p = Pattern.compile("^(http|https|ftp)://((\\w*)(\\.?))*(\\.)*");
		Matcher m = p.matcher(url);
		flag = m.matches();
		System.out.println("==========================" + flag);
		return flag;
		
	}

}
