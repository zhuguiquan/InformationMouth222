package com.bw.informationmouth;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bw.informationmouth.base.BaseFragment;

import java.util.List;

class MyAdapter extends BaseAdapter {
    private List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //获取数据类型
        int type = list.get(position).getType();
        if (type == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //条目类型
        int itemViewType = getItemViewType(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {

            if (itemViewType == 0) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.item_home, null);
                viewHolder.imageView = convertView.findViewById(R.id.img);
                viewHolder.name = convertView.findViewById(R.id.name);
                viewHolder.content = convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.item_home_left, null);
                viewHolder.imageView = convertView.findViewById(R.id.img);
                viewHolder.name = convertView.findViewById(R.id.name);
                viewHolder.content = convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //获取数据
        Bean.ListdataBean listdataBean = list.get(position);
        //绑定数据
        viewHolder.name.setText(listdataBean.getName());
        viewHolder.content.setText(listdataBean.getContent());
        NetUtil.getInstance().getPhoto(listdataBean.getAvatar(), viewHolder.imageView);
        return convertView;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView content;
    }

}
