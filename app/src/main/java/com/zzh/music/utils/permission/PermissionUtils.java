package com.zzh.music.utils.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

/**
 * Created by ZZH on 16/9/12.
 *
 * @Date: 16/9/12
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 权限管理
 */
public class PermissionUtils {

    private static final String TAG = "PermissionUtils";

    /**
     * 权限申请处理
     * @param act 当前上下文
     * @param permission 申请的权限
     * @param requestCode 请求返回值
     */
    public static void checkPermission(final Activity act, String permission, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int per = act.checkSelfPermission(permission);
            if (PackageManager.PERMISSION_GRANTED != per){
                boolean flag = act.shouldShowRequestPermissionRationale(permission);
                if (flag){
                    //弹窗设置
                    new AlertDialog.Builder(act)
                            .setMessage("app需要开启权限才能使用此功能")
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + act.getPackageName()));
                                    act.startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                } else {
                    ActivityCompat.requestPermissions(act,new String[]{permission}, requestCode);
                }

            }
        }

    }

}
