package com.bw.informationmouth.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.informationmouth.R;
import com.bw.informationmouth.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.tv);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        textView.setText(key);
    }

    public static OtherFragment getInstance(String value) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
