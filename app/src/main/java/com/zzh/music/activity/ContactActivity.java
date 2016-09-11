package com.zzh.music.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.zzh.libs.interfaces.OnItemClickListener;
import com.zzh.libs.side.bar.SideBar;
import com.zzh.libs.widget.AlertView;
import com.zzh.libs.widget.HRecyclerView;
import com.zzh.music.R;
import com.zzh.music.adapter.ContactsAdapter;
import com.zzh.music.base.BaseActivity;
import com.zzh.music.model.Contacts;
import com.zzh.music.utils.ContactsLoader;

import java.util.List;

/**
 * Created by ZZH on 16/6/17
 *
 * @Date: 16/6/17 14:49
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 读取手机通讯录信息
 */

public class ContactActivity extends BaseActivity {
    private static final int CODE_READ_CONTACT = 10000;
    private AlertView mAlertView;
    //private ObservableListView mListView;
    //ArrayAdapter<String> mAdapter = null;
    private HRecyclerView mHRecyclerView;
    TextView tv;
    SideBar mSideBar;
    ContactsAdapter mContactsAdapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
    }

    @Override
    protected void initView() {
        tv = (TextView) findViewById(R.id.tv);
        mSideBar = (SideBar) findViewById(R.id.sideBar);
        mHRecyclerView = (HRecyclerView) findViewById(R.id.lv_contacts);
        mAlertView = new AlertView(mContext, null, "请稍后...", null, new String[]{}, null, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object obj, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        mSideBar.setTextView(tv);
        mLayoutManager = new LinearLayoutManager(mContext);
        mContactsAdapter = new ContactsAdapter(mContext);
        mHRecyclerView.setLayoutManager(mLayoutManager);
        mHRecyclerView.setAdapter(mContactsAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},
                        CODE_READ_CONTACT);
            } else {
                List<Contacts> contactList = ContactsLoader.getInstance(mContext).getContactList();
                if (contactList != null){
                    mContactsAdapter.addAll(contactList);
                    mContactsAdapter.notifyDataSetChanged();
                }
            }
        } else {
            List<Contacts> contactList = ContactsLoader.getInstance(mContext).getContactList();
            if (contactList != null){
                mContactsAdapter.addAll(contactList);
                mContactsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_CONTACT:
                ContactsLoader.getInstance(mContext).getContactList();
                break;
        }
    }
}
