package com.bw.informationmouth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.informationmouth.base.BaseActivity;
import com.bw.informationmouth.fragment.OtherFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
//我添加了一行代码
    //我添加了一行代码
    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();

    @Override
    protected void initData() {
        Log.e("TAG", "initData  =======");
        titleList.add("首页");
        titleList.add("热点");
        titleList.add("北京");
        

        //A卷，三个页面都一样
        HomeFragment homeFragment = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(homeFragment1);
        fragmentList.add(homeFragment2);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });

        tabLayout = findViewById(R.id.tabl);
        viewPager = findViewById(R.id.vp);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
