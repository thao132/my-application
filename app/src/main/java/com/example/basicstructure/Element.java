package com.example.basicstructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {
    Bitmap bitmap;
    int mX = 10;
    int mY  = 10;

    public Element(Resources res, int x, int y) {
        bitmap = BitmapFactory.decodeResource(res, R.drawable.iconsbts);
        mX = x-bitmap.getWidth()/2;
        mY = y-bitmap.getHeight()/2;
    }

    public Element(Resources res, int x, int y, int idHinh) {
        bitmap = BitmapFactory.decodeResource(res, idHinh);
        mX = x-bitmap.getWidth()/2;
        mY = y-bitmap.getHeight()/2;
    }

    public void doDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, mX, mY, null);
    }


}
