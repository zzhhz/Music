package com.zzh.music.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.music.R;

/**
 * Created by ZZH on 16/7/5.
 *
 * @Date: 16/7/5
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class ContactsViewHolder extends RecyclerView.ViewHolder {
    public ImageView mContactHead;
    public TextView mContactName;
    public ContactsViewHolder(View itemView) {
        super(itemView);
        mContactHead = (ImageView) itemView.findViewById(R.id.iv_contact_head);
        mContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
    }
}