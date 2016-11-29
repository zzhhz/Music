package com.zzh.libs.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

/**
 * Created by ZZH on 16/6/30.
 *
 * @Date: 16/6/30
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 自定义的RecyclerView, 主要用于下拉刷新,和加载更多; 添加了一个监听上滑还是下滑的事件
 */
public class ZRecyclerView extends LRecyclerView {
    private static final String TAG = "HRecyclerView";
    private float pressY = 0;
    private OnItemScrollListener mOnItemScrollListener;
    public ZRecyclerView(Context context) {
        super(context);
    }

    public ZRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (!isEnabled()){
            return isClickable() || isLongClickable();
        }

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                pressY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = e.getY() - pressY;
                if (mOnItemScrollListener != null) {
                    if (moveY > 0) {
                        mOnItemScrollListener.scrollDown();
                    } else if (moveY == 0) {
                        mOnItemScrollListener.scrollStop();
                    } else {
                        mOnItemScrollListener.scrollUp();
                    }
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    public void setOnItemScrollListener(OnItemScrollListener listener){
        this.mOnItemScrollListener = listener;
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);
    }



    public interface OnItemScrollListener{
        void scrollUp(); //上滑
        void scrollStop(); //停止
        void scrollDown(); //下拉
    }
}
