package com.zzh.libs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzh.libs.R;

import java.util.List;

/**
 * Created by ZZH on 16/4/14.
 */
public class AlertViewAdapter extends BaseAdapter {
    private List<String> mDatas;
    private List<String> mDestrutive;

    public AlertViewAdapter(List<String> mDatas, List<String> mDestrutive) {
        this.mDatas = mDatas;
        this.mDestrutive = mDestrutive;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String data = (String) getItem(position);
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alertbutton, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.updateUI(parent.getContext(), data, position);
        return convertView;
    }

    class ViewHolder {
        TextView tvAlert;

        public ViewHolder(View itemView) {
            tvAlert = (TextView) itemView.findViewById(R.id.tvAlert);
        }

        public void updateUI(Context ctx, String data, int position) {
            tvAlert.setText(data);
            if (mDestrutive != null && mDestrutive.contains(data)) {
                tvAlert.setTextColor(ctx.getResources().getColor(R.color.textColor_alert_button_destructive));
            } else {
                tvAlert.setTextColor(ctx.getResources().getColor(R.color.textColor_alert_button_others));
            }
        }


    }


}
