package com.limei.movieapp.huiying.fangying;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/15.
 */

/**
 * 有不同
 */

public class MyFangYingFragment
        extends Fragment {
    private MyTabAdapter adapter;
    @Bind({R.id.jijiang})
    TextView jijiang;
    private MyzhengzaifangFragment myzhengzaifangFragment = new MyzhengzaifangFragment();
    @Bind({R.id.tablayout})
    TabLayout tablayout;
    @Bind({R.id.viewpager})
    ViewPager viewpager;
    @Bind({R.id.zhengzai})
    TextView zhengzai;

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(myzhengzaifangFragment);
        add(new MyWanChengFangYingFragment());
    }};

    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("1");
        add("2");
    }};

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        StatusBarUtil.setStatusBarColor(getActivity(), R.color.wight);
        View view = paramLayoutInflater.inflate(R.layout.fragment_fangying, null);
        ButterKnife.bind(this, view);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("asdsadasdsa", tab.getText().toString());
                if (tab.getText().toString().equals("1")) {
                    zhengzai.setBackgroundResource(R.drawable.banbuttonblack);
                    int color1 = getContext().getResources().getColor(R.color.wight);
                    zhengzai.setTextColor(color1);
                    jijiang.setBackgroundResource(R.drawable.banbuttonwight);
                    int color2 = getContext().getResources().getColor(R.color.black);
                    jijiang.setTextColor(color2);
                }
                if (tab.getText().toString().equals("2")) {
                    jijiang.setBackgroundResource(R.drawable.banbuttonblackright);
                    int color3 = getContext().getResources().getColor(R.color.wight);
                    jijiang.setTextColor(color3);
                    zhengzai.setBackgroundResource(R.drawable.banbuttonwightright);
                    int color4 = getContext().getResources().getColor(R.color.black);
                    zhengzai.setTextColor(color4);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        this.adapter = new MyTabAdapter(getChildFragmentManager(), this.titleList, this.fragmentList);
        this.viewpager.setAdapter(this.adapter);
        this.tablayout.setupWithViewPager(this.viewpager);
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onHiddenChanged(boolean paramBoolean) {
        super.onHiddenChanged(paramBoolean);
        Log.i("fsdf", "onHiddenChanged");
        if (paramBoolean == true) {
            //myzhengzaifangFragment.getData();
        }
    }

    @OnClick({R.id.zhengzai, R.id.jijiang, R.id.tablayout, R.id.viewpager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhengzai:
                zhengzai.setBackgroundResource(R.drawable.banbuttonblack);
                int color1 = getContext().getResources().getColor(R.color.wight);
                zhengzai.setTextColor(color1);
                jijiang.setBackgroundResource(R.drawable.banbuttonwight);
                int color2 = getContext().getResources().getColor(R.color.black);
                jijiang.setTextColor(color2);
                viewpager.setCurrentItem(0);
                break;
            case R.id.jijiang:
                jijiang.setBackgroundResource(R.drawable.banbuttonblackright);
                int color3 = getContext().getResources().getColor(R.color.wight);
                jijiang.setTextColor(color3);
                zhengzai.setBackgroundResource(R.drawable.banbuttonwightright);
                int color4 = getContext().getResources().getColor(R.color.black);
                zhengzai.setTextColor(color4);
                viewpager.setCurrentItem(1);
                break;
            case R.id.tablayout:
                break;
            case R.id.viewpager:
                break;
        }
    }

    public class MyTabAdapter extends FragmentPagerAdapter {

        private ArrayList<String> titleList;
        private ArrayList<Fragment> fragmentList;

        public MyTabAdapter(FragmentManager fm, ArrayList<String> titleList, ArrayList<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
