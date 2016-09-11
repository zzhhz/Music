package com.zzh.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.music.R;
import com.zzh.music.holder.ContactsViewHolder;
import com.zzh.music.model.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/7/5.
 *
 * @Date: 16/7/5
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private final Context mContext;
    private List<Contacts> dataList;

    public ContactsAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Contacts item) {
        dataList.add(item);
    }

    public void addAll(List<Contacts> list) {
        dataList.addAll(list);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ContactsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        Contacts item = dataList.get(position);
        holder.mContactName.setText(item.getUserName());
        holder.mContactHead.setImageBitmap(item.getUserHead());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}