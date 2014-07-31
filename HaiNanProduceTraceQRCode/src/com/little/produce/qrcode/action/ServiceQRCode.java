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

	//ͼƬ��ȵ�һ��
	private static final int IMAGE_HALFWIDTH = 20;
	//���뵽��ά�������ͼƬ����
	private static Bitmap mBitmap = null;
	//��Ҫ��ͼͼƬ�Ĵ�С �����趨Ϊ40*40
	int[] pixels = new int[2*IMAGE_HALFWIDTH * 2*IMAGE_HALFWIDTH];
	
	//---���ɶ�ά��
	public static Bitmap create(String content, Activity mThis) throws Exception {
		 // ������Ҫ�����ͼƬ����
		mBitmap = ((BitmapDrawable) mThis.getResources().getDrawable(R.drawable.ic_qrcode)).getBitmap();
		// ����ͼƬ
		Matrix m = new Matrix();
		float sx = (float) 2*IMAGE_HALFWIDTH / mBitmap.getWidth();
		float sy = (float) 2*IMAGE_HALFWIDTH / mBitmap.getHeight();

		m.setScale(sx, sy);
		// ���¹���һ��40*40��ͼƬ
		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
				mBitmap.getHeight(), m, false);
		
		//---Ҫע��String�ı������⣬������������
		return createQRBmp(new String(content.getBytes(), "ISO-8859-1"));
	}
	
	public static Bitmap createQRBmp(String str) throws WriterException {
		//���ɶ�ά����,����ʱָ����С,��Ҫ������ͼƬ�Ժ��ٽ�������,������ģ������ʶ��ʧ��
		//����BitMatrix������ʵ����һ�������͵�����
		//encode()����������Ĳ�����1��������ַ���  2�����������(��ά�룬������...)   3����������Ĵ�С(�����ڷֱ���)
		BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// ��ά����תΪһά��������,Ҳ����һֱ��������
		int halfW = width / 2;
		int halfH = height / 2;
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				//��ͼƬ���Ƶ�ָ��������
				//���ǽ�ͼƬ���ص���ɫֵд�뵽��Ӧ�±��������
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
		// ͨ��������������bitmap
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

		return bitmap;
		
	}
}
