package com.zzh.libs.anims;

import android.view.Gravity;

import com.zzh.libs.R;

/**
 * Created by ZZH on 16/4/14.
 */
public class AlertAnimateUtils {
    private final static int INVALID = -1;

    public static int getAnimation(int grivaty, boolean isInAnimation) {
        switch (grivaty) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.fade_in_center : R.anim.fade_out_center;
        }
        return INVALID;

    }
}
