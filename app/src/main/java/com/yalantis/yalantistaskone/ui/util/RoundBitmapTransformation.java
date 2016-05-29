package com.yalantis.yalantistaskone.ui.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.squareup.picasso.Transformation;

/**
 * Created by Антон on 27.05.2016.
 */
public class RoundBitmapTransformation implements Transformation {
    private static final Paint paintMask = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint paintBitmap = new Paint();

    private static final String KEY = "round_image";

    static {
        paintMask.setColor(Color.BLACK);
        paintMask.setStyle(Paint.Style.FILL);
        paintMask.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        paintBitmap.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    public Bitmap transform(Bitmap bitmapIn) {
        if (bitmapIn == null || bitmapIn.isRecycled())
            return bitmapIn;

        final int squareSide = Math.min(bitmapIn.getWidth(), bitmapIn.getHeight());
        Bitmap bitmapOut = Bitmap.createBitmap(squareSide, squareSide, Bitmap.Config.ARGB_8888);

        if (bitmapOut == null)
            return bitmapIn;

        try {
            final float radius = Math.min(bitmapOut.getWidth(), bitmapOut.getHeight()) / 2.f;

            Canvas canvas = new Canvas(bitmapOut);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawCircle(radius, radius, radius, paintMask);
            canvas.drawBitmap(bitmapIn, 0, 0, paintBitmap);

            return bitmapOut;
        } finally {
            bitmapIn.recycle();
        }
    }

    @Override
    public String key() {
        return KEY;
    }
}
