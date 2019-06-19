package com.limei.movieapp.huiying.goupiao;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.fenlei.MyFenLeiActivity;
import com.limei.movieapp.huiying.sousuo.MySouSuoActivity;
import com.limei.movieapp.huiying.unit.StatusBarUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/13.
 */

public class MyGoupiaoFragment extends Fragment {

    @Bind(R.id.fenlei)
    ImageView fenlei;
    @Bind(R.id.zhengzai)
    TextView zhengzai;
    @Bind(R.id.jijiang)
    TextView jijiang;
    @Bind(R.id.sousuo)
    ImageView sousuo;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tablayout;

    private MyTabAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new MyzhengzaiFragment());
        add(new MyJijiangFragment());
    }};

    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("1");
        add("2");
    }};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(getActivity(), R.color.wight);
        View view = inflater.inflate(R.layout.goupiaofragment, null);
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
        adapter = new MyTabAdapter(getChildFragmentManager(), titleList, fragmentList);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.fenlei, R.id.zhengzai, R.id.jijiang, R.id.sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fenlei:
                startActivity(new Intent(getContext(), MyFenLeiActivity.class));
                break;
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
            case R.id.sousuo:
                startActivity(new Intent(getContext(), MySouSuoActivity.class));
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

/**
 * 有不同
 */




/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\goupiao\MyGoupiaoFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

