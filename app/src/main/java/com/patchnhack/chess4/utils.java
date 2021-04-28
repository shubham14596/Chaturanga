package com.patchnhack.chess4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.TypedValue;

public class utils{
    public static int dpi(int p, Context mContext){
//        final float scale = getContext().getResources().getDisplayMetrics().density;
//        int pixels = (int) (dps * scale + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, p, mContext.getResources().getDisplayMetrics());
    }
    public static Bitmap tint(Bitmap bitmap, int color) {
//        Paint paint = new Paint();
//        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
//        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmapResult);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        return bitmapResult;

        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int [] allpixels = new int [bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(allpixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for(int i = 0; i < allpixels.length; i++)
            if(allpixels[i] == Color.WHITE)
                allpixels[i] = color;
        bmp.setPixels(allpixels,0,bitmap.getWidth(),0, 0, bitmap.getWidth(),bitmap.getHeight());
        return bmp;

    }
}