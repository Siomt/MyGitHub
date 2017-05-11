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
    private List<TrendingBean> list;

    public AdapterList(List list, Context context) {
        super();
        this.list = list;
        this.inflater = LayoutInflater.from(context);

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
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_tv);
            viewHolder.synopsis = (TextView) convertView.findViewById(R.id.synopsis_tv);
            viewHolder.language = (TextView) convertView.findViewById(R.id.language_tv);
            viewHolder.totalStar = (TextView) convertView.findViewById(R.id.total_star_tv);
            viewHolder.todayStar = (TextView) convertView.findViewById(R.id.today_star_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.synopsis.setText(list.get(position).getSynopsis());
        viewHolder.language.setText(list.get(position).getProgrammingLanguage());
        viewHolder.totalStar.setText(list.get(position).getTotalStar());
        viewHolder.todayStar.setText(list.get(position).getTodayStar());

        return convertView;
    }
    class ViewHolder {
        public TextView title;
        public TextView synopsis;
        public TextView language;
        public TextView totalStar;
        public TextView todayStar;
    }
}
