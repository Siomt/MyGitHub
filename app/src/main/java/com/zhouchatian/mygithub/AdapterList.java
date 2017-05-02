package com.zhouchatian.mygithub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mr.Robot on 2017/5/2.
 * https://github.com/TheSadFrog
 */

public class AdapterList extends BaseAdapter {
    private LayoutInflater inflater;
    private List list;

    public AdapterList(List list, Context context) {
        super();
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        if (null != list) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_item, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.img_type);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.size = (TextView) convertView.findViewById(R.id.desc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).toString());
//        viewHolder.image.setImageResource(list.get(position).getType());
//        viewHolder.size.setText(list.get(position).getSize());
        return convertView;
    }
    class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView size;
    }
}
