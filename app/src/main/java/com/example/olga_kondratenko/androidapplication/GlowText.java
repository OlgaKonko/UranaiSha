package com.example.olga_kondratenko.androidapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

public class GlowText extends TextView {
        public GlowText(Context context) {
            super(context);
        }

        public GlowText(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public GlowText(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            super.onDraw(canvas);
            super.onDraw(canvas);
        }

}
