package com.zzh.music.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.music.MusicApplication;
import com.zzh.music.R;

/**
 * Created by ZZH on 16/11/27.
 *
 * @Date: 16/11/27
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 音乐播放的时候类似CD的转盘
 */

public class CDView extends View {

    private static final int MSG_RUN = 0x00000100;
    private static final int TIME_UPDATE = 50;

    private Bitmap mCircleBitmap;
    private Bitmap mClipBitmap; // cd图片

    private float mRotation = 0.0f;

    private Matrix mMatrix;

    private volatile boolean isRunning;
    private int mWidth = 0; //宽高
    private int mHeight = 0;

    public void setWidthAndHeight(int mWidth, int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public CDView(Context context) {
        this(context, null, 0);
    }

    public CDView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CDView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCircleBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mClipBitmap == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int width = 0;
        int height = 0;

        // mode of width
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // mode of height
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // size of width
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // size of height
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // parent assign the size
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            // child compute the size of itself
            width = mClipBitmap.getWidth();
            // parent assign the max size, child should <= widthSize
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mClipBitmap.getHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mClipBitmap == null)
            return;
        canvas.save();
        mMatrix.setRotate(mRotation, mWidth / 2,
                mHeight / 2);
        canvas.drawBitmap(mClipBitmap, mMatrix, null);
        canvas.drawBitmap(mCircleBitmap,
                mWidth,
                mHeight, null);
        canvas.restore();
    }

    /**
     * 创建圆形剪切图
     *
     * @param src
     * @return
     */
    private Bitmap createCircleBitmap(Bitmap src) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(255, 241, 239, 229);

        Bitmap target = Bitmap.createBitmap(mWidth,
                mHeight, Bitmap.Config.ARGB_8888);
        src = Bitmap.createScaledBitmap(src, mWidth, mHeight, false);
        Canvas canvas = new Canvas(target);

        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, 0, 0, paint);

        return target;
    }

    /**
     * 设置cd图片
     *
     * @param bmp
     */
    public void setImage(Bitmap bmp) {
        int widthSize = bmp.getWidth();
        int heightSize = bmp.getHeight();
        int widthSpec = MeasureSpec.makeMeasureSpec(widthSize,
                MeasureSpec.AT_MOST);
        int heightSpec = MeasureSpec.makeMeasureSpec(heightSize,
                MeasureSpec.AT_MOST);

        measure(widthSpec, heightSpec);

        mClipBitmap = createCircleBitmap(bmp);
        requestLayout();
        invalidate();
    }

    /**
     * 开始旋转
     */
    public void start() {
        if (isRunning)
            return;
        isRunning = true;
        mHandler.sendEmptyMessageDelayed(MSG_RUN, TIME_UPDATE);
    }

    /**
     * 暂停旋转
     */
    public void pause() {
        if (!isRunning)
            return;
        isRunning = false;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MSG_RUN) {
                if (isRunning) {
                    mRotation += 0.1f;
                    if (mRotation >= 360)
                        mRotation = 0;
                    invalidate();
                    sendEmptyMessageDelayed(MSG_RUN, TIME_UPDATE);
                }
            }
        }
    };
}
