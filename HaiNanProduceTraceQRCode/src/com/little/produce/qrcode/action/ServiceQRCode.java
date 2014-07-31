package com.little.produce.qrcode.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.little.produce.qrcode.*;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class ServiceQRCode {

	//图片宽度的一般
	private static final int IMAGE_HALFWIDTH = 20;
	//插入到二维码里面的图片对象
	private static Bitmap mBitmap = null;
	//需要插图图片的大小 这里设定为40*40
	int[] pixels = new int[2*IMAGE_HALFWIDTH * 2*IMAGE_HALFWIDTH];
	
	//---生成二维码
	public static Bitmap create(String content, Activity mThis) throws Exception {
		 // 构造需要插入的图片对象
		mBitmap = ((BitmapDrawable) mThis.getResources().getDrawable(R.drawable.ic_qrcode)).getBitmap();
		// 缩放图片
		Matrix m = new Matrix();
		float sx = (float) 2*IMAGE_HALFWIDTH / mBitmap.getWidth();
		float sy = (float) 2*IMAGE_HALFWIDTH / mBitmap.getHeight();

		m.setScale(sx, sy);
		// 重新构造一个40*40的图片
		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
				mBitmap.getHeight(), m, false);
		
		//---要注意String的编码问题，否则会出现乱码
		return createQRBmp(new String(content.getBytes(), "ISO-8859-1"));
	}
	
	public static Bitmap createQRBmp(String str) throws WriterException {
		//生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		//返回BitMatrix对象，其实就是一个布尔型的数组
		//encode()方法穿传入的参数：1、编码的字符串  2、编码的类型(二维码，条形码...)   3、返回数组的大小(类似于分辨率)
		BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int halfW = width / 2;
		int halfH = height / 2;
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				//将图片绘制到指定区域中
				//就是将图片像素的颜色值写入到相应下标的数组中
				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH && y > halfH - IMAGE_HALFWIDTH
						&& y < halfH + IMAGE_HALFWIDTH) {
					pixels[y * width + x] = mBitmap.getPixel(x - halfW + IMAGE_HALFWIDTH, y
							- halfH + IMAGE_HALFWIDTH);
				} else {
					if (matrix.get(x, y)) {
						
						pixels[y * width + x] = 0xEE000000;
						
					}else{
						
						pixels[y * width + x] = 0xffffffff;
												
					}
				}

			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

		return bitmap;
		
	}
}
