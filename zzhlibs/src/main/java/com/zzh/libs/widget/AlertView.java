package com.zzh.libs.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zzh.libs.R;
import com.zzh.libs.adapter.AlertViewAdapter;
import com.zzh.libs.anims.AlertAnimateUtils;
import com.zzh.libs.interfaces.OnDismissListener;
import com.zzh.libs.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/6/22
 *
 * @Date: 16/6/22 19:41
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 对话弹出框<br/>
 * 1.AlertViewAdapter<br/>
 * 2.AlertAnimateUtils<br/>
 * 3.OnDismissListener<br />
 * 4.OnItemClickListener<br/>
 */
public class AlertView {

    private static final int HORIZONTAL_BUTTON_MAX_COUNT = 2;
    public static final int CANCELPOSITION = -1;
    private static final String TAG = AlertView.class.getName();
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

    public enum Style {
        ActionSheet, Alert
    }

    /****
     * 数据源
     *****/
    private String title; //标题
    private String msg; //提示信息
    private String cancel; //取消
    private ArrayList<String> mDatas = new ArrayList<>();
    private ArrayList<String> mDestructive;

    /*****
     * 使用到的view
     ******/
    private Context mContext;
    private ViewGroup decorGroup; //activity 的根view
    private ViewGroup rootView;//alert view的根view
    private ViewGroup loAlertView;//head view
    private ViewGroup contentContainer;

    //弹出方式
    private Style style = Style.Alert;

    //监听事件

    private OnDismissListener dismissListener;
    private OnItemClickListener clickListener;
    private boolean isDismissing;


    //动画, 进入动画, 推出动画
    private Animation inAnim;
    private Animation outAnim;

    private int gravity = Gravity.CENTER;


    public AlertView(Context mContext, String title, String msg, String cancel, String[] mDestructive,
                     String[] mOthers, Style style, OnItemClickListener clickListener
    ) {
        this.mDatas = new ArrayList<>();
        this.mDestructive = new ArrayList<>();
        this.mContext = mContext;
        this.clickListener = clickListener;
        if (style != null) {
            this.style = style;
        }
        ArrayList<String> tmpDest = (ArrayList<String>) array2List(mDestructive);
        ArrayList<String> tmpData = (ArrayList<String>) array2List(mOthers);
        initData(title, msg, cancel, tmpDest, tmpData);
        initViews();
        init();
    }

    private List<String> array2List(String[] array) {
        if (array == null) {
            return null;
        }
        ArrayList<String> tmp = new ArrayList<>();
        for (String arr :
                array) {
            tmp.add(arr);
        }
        return tmp;
    }

    public AlertView(Context mContext, String title, String msg, String cancel, ArrayList<String> mDestructive,
                     ArrayList<String> mOthers, Style style, OnItemClickListener clickListener
    ) {
        mDatas = new ArrayList<>();
        mDestructive = new ArrayList<>();
        this.mContext = mContext;
        this.clickListener = clickListener;
        if (style != null) {
            this.style = style;
        }
        initData(title, msg, cancel, mDestructive, mOthers);
        initViews();
        init();

    }

    protected void init() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    private Animation getOutAnimation() {
        int res = AlertAnimateUtils.getAnimation(this.gravity, false);
        return AnimationUtils.loadAnimation(mContext, res);
    }

    public AlertView setOnDismissListener(OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
        return this;
    }

    /**
     * 主要用于拓展View的时候有输入框，键盘弹出则设置MarginBottom往上顶，避免输入法挡住界面
     */
    public void setMarginBottom(int marginBottom) {
        int margin_alert_left_right = mContext.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
        params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, marginBottom);
        contentContainer.setLayoutParams(params);
    }

    private Animation getInAnimation() {
        int res = AlertAnimateUtils.getAnimation(this.gravity, true);
        return AnimationUtils.loadAnimation(mContext, res);
    }

    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorGroup = (ViewGroup) ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview, decorGroup, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        int margin_alert_left_right = 0;
        switch (style) {
            case ActionSheet:
                params.gravity = Gravity.BOTTOM;
                margin_alert_left_right = mContext.getResources().getDimensionPixelSize(R.dimen.margin_actionsheet_left_right);
                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, margin_alert_left_right);
                contentContainer.setLayoutParams(params);
                gravity = Gravity.BOTTOM;
                initActionSheetViews(layoutInflater);
                break;
            case Alert:
                params.gravity = Gravity.CENTER;
                margin_alert_left_right = mContext.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, 0);
                contentContainer.setLayoutParams(params);
                gravity = Gravity.CENTER;

                initAlertView(layoutInflater);

                break;
        }
    }

    private void initAlertView(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_alert, contentContainer);
        initHeaderView(viewGroup);

        int position = 0;
        //如果总数据小于等于HORIZONTAL_BUTTONS_MAXCOUNT，则是横向button
        if (mDatas.size() <= HORIZONTAL_BUTTON_MAX_COUNT) {
            ViewStub viewStub = (ViewStub) contentContainer.findViewById(R.id.viewStubHorizontal);
            viewStub.inflate();
            LinearLayout loAlertButtons = (LinearLayout) contentContainer.findViewById(R.id.loAlertButtons);
            for (int i = 0; i < mDatas.size(); i++) {
                //如果不是第一个按钮
                if (i != 0) {
                    //添加上按钮之间的分割线
                    View divier = new View(mContext);
                    divier.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor_divier));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) mContext.getResources().getDimension(R.dimen.size_divier), LinearLayout.LayoutParams.MATCH_PARENT);
                    loAlertButtons.addView(divier, params);
                }

                View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_alertbutton, null);
                TextView tvAlert = (TextView) itemView.findViewById(R.id.tvAlert);
                tvAlert.setClickable(true);

                //设置点击效果
                if (mDatas.size() == 1) {
                    tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
                } else if (i == 0) {//设置最左边的按钮效果
                    tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_left);
                } else if (i == mDatas.size() - 1) {//设置最右边的按钮效果
                    tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_right);
                }
                String data = mDatas.get(i);
                tvAlert.setText(data);

                //取消按钮的样式
                if (data == cancel) {
                    tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
                    tvAlert.setTextColor(mContext.getResources().getColor(R.color.textColor_alert_button_cancel));
                    tvAlert.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
                    position = position - 1;
                }
                //高亮按钮的样式
                else if (mDestructive != null && mDestructive.contains(data)) {
                    tvAlert.setTextColor(mContext.getResources().getColor(R.color.textColor_alert_button_destructive));
                }

                tvAlert.setOnClickListener(new OnTextClickListener(position));
                position++;
                loAlertButtons.addView(itemView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            }
        } else {
            ViewStub viewStub = (ViewStub) contentContainer.findViewById(R.id.viewStubVertical);
            viewStub.inflate();
            initListView();
        }
    }

    private void initActionSheetViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_sheet, contentContainer);
        //头
        initHeaderView(viewGroup);
        //选项
        initListView();
    }

    private void initListView() {
        ListView alertButtonListView = (ListView) contentContainer.findViewById(R.id.alertButtonListView);
        if (!TextUtils.isEmpty(cancel) && Style.Alert == style) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_alertbutton, null);
            TextView tvAlert = (TextView) itemView.findViewById(R.id.tvAlert);
            tvAlert.setClickable(true);
            tvAlert.setText(cancel);
            tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
            tvAlert.setTextColor(mContext.getResources().getColor(R.color.textColor_alert_button_cancel));
            tvAlert.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
            tvAlert.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
        }
        //List view的数据

        AlertViewAdapter adapter = new AlertViewAdapter(mDatas, mDestructive);

        alertButtonListView.setAdapter(adapter);
        alertButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (clickListener != null) {
                    clickListener.onItemClick(AlertView.this, position);
                }
                dismiss();
            }
        });
    }

    /**
     * 初始化标题,和提示信息,
     */
    private void initHeaderView(ViewGroup viewGroup) {
        loAlertView = (ViewGroup) viewGroup.findViewById(R.id.loAlertHeader);
        TextView tvAlertTitle = (TextView) loAlertView.findViewById(R.id.tvAlertTitle);
        TextView tvAlertMsg = (TextView) loAlertView.findViewById(R.id.tvAlertMsg);
        if (!TextUtils.isEmpty(title)) {
            tvAlertTitle.setText(title);
        } else {
            tvAlertTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(msg)) {
            tvAlertMsg.setText(msg);
        } else {
            tvAlertMsg.setVisibility(View.GONE);
        }
    }

    private void initData(String title, String msg, String cancel, ArrayList<String> mDestructive, ArrayList<String> mOthers) {
        this.title = title;
        this.msg = msg;
        this.cancel = cancel;
        if (mDestructive != null) {
            this.mDestructive = mDestructive;
            this.mDatas.addAll(mDestructive);
        }
        if (mOthers != null) {
            this.mDatas.addAll(mOthers);
        }

        if (cancel != null) {
            this.cancel = cancel;
            if (style == Style.Alert && this.mDatas.size() < HORIZONTAL_BUTTON_MAX_COUNT) {
                this.mDatas.add(0, cancel);
            }
        }
    }

    class OnTextClickListener implements View.OnClickListener {

        private int position;

        public OnTextClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(v, position);
                dismiss();
            }
        }
    }

    public void dismiss() {
        if (isDismissing) {
            return;
        }
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                decorGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        decorGroup.removeView(rootView);
                        isDismissing = false;
                        if (dismissListener != null) {
                            dismissListener.onDismiss(AlertView.this);
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        contentContainer.startAnimation(outAnim);
        isDismissing = true;
    }

    public AlertView setCancelable(boolean isCancelable) {
        View view = rootView.findViewById(R.id.outmost_container);

        if (isCancelable) {
            view.setOnTouchListener(onCancelableTouchListener);
        } else {
            view.setOnTouchListener(null);
        }
        return this;
    }

    /**
     * Called when the user touch on black overlay in order to dismiss the dialog
     */
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };

    public void show() {
        if (isShowing()) {
            return;
        }
        onAttach(rootView);
    }

    private void onAttach(ViewGroup rootView) {
        decorGroup.addView(rootView);
        contentContainer.startAnimation(inAnim);
    }

    public boolean isShowing() {
        View view = decorGroup.findViewById(R.id.outmost_container);

        return (view != null);
    }


    public AlertView addExtView(View extView) {
        loAlertView.addView(extView);
        return this;
    }


}
