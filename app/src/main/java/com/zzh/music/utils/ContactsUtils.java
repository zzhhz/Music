package com.zzh.music.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.zzh.music.model.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/6/22.
 *
 * @Date: 16/6/22
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 读取通讯录联系人信息
 */
public class ContactsUtils {

    public static List<Contacts> selectContactsMsg(ContentResolver resolver, ContentValues values) {
        List<Contacts> contactsList = new ArrayList<>();
        String uri_contacts = "content://com.android.contacts/raw_contacts";
        String uri_contacts_phones = "content://com.android.contacts/data/phones";
        // 从raw_contacts表中或许联系人的id和联系人的姓名。
        Cursor cursor_contacts = resolver.query(Uri.parse(uri_contacts), new String[]{"_id", "display_name"}, null, null, null);
        // 遍历所有的联系人的信息
        while (cursor_contacts.moveToNext()) {

            int contacts_id = cursor_contacts.getInt(cursor_contacts.getColumnIndex("_id"));

            String display_name = cursor_contacts.getString(cursor_contacts.getColumnIndex("display_name"));

            Contacts contacts = new Contacts();
            contacts.setUserId(contacts_id+"");
            contacts.setUserName(display_name);
// 以下开始获取电话号码
// 根据每个联系人的id再去data表中查找相应的电话号码。
            Cursor cursor_phones = resolver.query(Uri.parse(uri_contacts_phones),
                    new String[]{"raw_contact_id", "data1"}, "raw_contact_id=?",
                    new String[]{contacts_id + ""}, null);

// 因为电话号码可能是多个，所以需要再遍历，组合在一起形成一个电话号码的字符串，放到StringBuilder中
            StringBuilder sb = new StringBuilder();
            while (cursor_phones.moveToNext()) {
                sb.append(cursor_phones.getString(1));
                sb.append(" | ");
            }
// 将生成的电话号码放到map集合中
            contacts.setPhoneNumber(sb.toString());
// 将包含有id、联系人姓名、手机号码、emails的map放到list集合中
            contactsList.add(contacts);
        }
        return contactsList;
    }
}
