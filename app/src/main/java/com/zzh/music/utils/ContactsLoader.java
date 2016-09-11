package com.zzh.music.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.zzh.music.R;
import com.zzh.music.model.Contacts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/7/5.
 *
 * @Date: 16/7/5
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 查询本地的数据库
 */
public class ContactsLoader {
    private static ContactsLoader mInstance;
    private static ContentResolver mResolver;
    private static Context mContext;
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private Uri contactHeadUri = ContactsContract.Contacts.CONTENT_URI;

    public static ContactsLoader getInstance(Context ctx) {
        synchronized (MusicLoader.class) {
            if (mInstance == null) {
                mContext = ctx;
                mResolver = ctx.getContentResolver();
                mInstance = new ContactsLoader();
            }
        }
        return mInstance;
    }


    /**
     * @return
     */
    public List<Contacts> getContactList() {
        List<Contacts> contactsList = null;
        Cursor cursor = mResolver.query(phoneUri, new String[]{"display_name", "data1", "sort_key", "contact_id"},
                null, null, "sort_key");
        if (cursor != null && cursor.moveToFirst()) {
            contactsList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String key = cursor.getColumnName(2);
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                long contactId = cursor.getLong(3);

                Uri headUri = ContentUris.withAppendedId(contactHeadUri, contactId);
                Contacts contacts = new Contacts();
                contacts.setPhoneNumber(phone);
                contacts.setUserName(name);
                Bitmap bitmap = null;
                InputStream headIs = ContactsContract.Contacts.openContactPhotoInputStream(mResolver, headUri);
                if (headIs != null) {
                    //去读
                    bitmap = BitmapFactory.decodeStream(headIs);
                } else {
                    //用程序的默认图标
                    bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
                }
                contacts.setUserHead(bitmap);
                contactsList.add(contacts);
                Log.d("--", "getContactList: " + name + ",phone--->" + phone + ", --contactId->: " + contactId);
            }
        }

        return contactsList;
    }
}
