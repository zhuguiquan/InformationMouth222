package com.bw.informationmouth;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bw.informationmouth.base.BaseFragment;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private XListView xListView;
    private int page = 1;
    List<Bean.ListdataBean> list = new ArrayList<>();
    private ImageView imageView;

    @Override
    protected void initView(final View inflate) {
        xListView = inflate.findViewById(R.id.xlv);
        imageView = inflate.findViewById(R.id.img);
        //支持上下拉对比：  pullToRefresh 是setMode(BOTH);  xlistView 是setPull/setPull
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                // TODO:清除旧数据
                getData();
                xListView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();
                xListView.stopLoadMore();
            }
        });

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("key", list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        if (NetUtil.getInstance().hasNet(getActivity())) {
            xListView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            String httpUrl = "";
            if (page == 1) {
                httpUrl = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
            } else if (page == 2) {
                httpUrl = "http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
            } else {
                httpUrl = "http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
            }
            NetUtil.getInstance().getJson(httpUrl, new NetUtil.MyCallback() {
                @Override
                public void onGetJson(String json) {
                    Log.i("TAG", "获取到了json" + json);

                    Bean bean = new Gson().fromJson(json, Bean.class);
                    //新数据
                    List<Bean.ListdataBean> newList = bean.getListdata();
                    list.addAll(newList);
                    xListView.setAdapter(new MyAdapter(list));
                }
            });
        } else {
            xListView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        // TODO: 2019/11/21 这个必须调用
        getData();
    }

}
