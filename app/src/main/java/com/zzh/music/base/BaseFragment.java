package com.zzh.music.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by ZZH on 16/12/21
 *
 * @Date: 16/12/21 14:16
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 支持Palette
 */
public abstract class BaseFragment extends com.zzh.zlibs.base.BaseFragment {
    //访问读写权限
    protected static final int WRITE_EXTERNAL_STORAGE = 10001;

    public abstract String getTitle();

    //6.0之上的权限管理
    protected void checkPermission(String permission, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int per = getActivity().checkSelfPermission(permission);
            if (PackageManager.PERMISSION_GRANTED != per){
                boolean flag = getActivity().shouldShowRequestPermissionRationale(permission);
                if (flag){
                    //弹窗设置
                    new AlertDialog.Builder(getActivity())
                            .setMessage("app需要开启权限才能使用此功能")
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                    getActivity().startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                }

            } else {
                doNextPermission(requestCode);
            }
        } else {
            doNextPermission(requestCode);
        }
    }

    //授予权限，或者已经取得权限都会走这个方法
    protected void doNextPermission(int requestCode){}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (verifyPermissions(grantResults)){
            doNextPermission(requestCode);
        } else {

        }
    }

    //验证是否授予权限
    public boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public int getBackgroundBitmapPosition(){

        return 0;
    }
}
