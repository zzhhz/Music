package com.zzh.music.model;

import android.graphics.Bitmap;
import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by ZZH on 16/6/17.
 *
 * @Date: 16/6/17
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 通讯录信息
 */
public class Contacts {
    private String userId;
    private String userName;
    private String phoneNumber;
    private String userNameChar;
    private Bitmap userHead;

    public String getUserName() {
        if (TextUtils.isEmpty(userName)){
            userName = phoneNumber;
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserNameChar() throws BadHanyuPinyinOutputFormatCombination {
        String tmp = getUserName();
        if (TextUtils.isEmpty(tmp)){
            return "#";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        String strings = PinyinHelper.toHanYuPinyinString(tmp, format,"",false);
        return strings;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Bitmap getUserHead() {
        return userHead;
    }

    public void setUserHead(Bitmap userHead) {
        this.userHead = userHead;
    }
}
