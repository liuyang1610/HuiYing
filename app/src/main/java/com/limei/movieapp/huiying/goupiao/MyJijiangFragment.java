package com.limei.movieapp.huiying.goupiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.JijiangshangyingInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/13.
 */


/**
 * 有不同
 */


public class MyJijiangFragment
        extends Fragment {
    private MyGouPiaoAdapter adapter;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                if (MyJijiangFragment.this.swipeRe.isRefreshing()) {
                    MyJijiangFragment.this.adapter.notifyDataSetChanged();
                    MyJijiangFragment.this.swipeRe.setRefreshing(false);
                }
                MyJijiangFragment.this.nulldata.setVisibility(View.GONE);
            }
            try {
                view = LayoutInflater.from(MyJijiangFragment.this.getContext()).inflate(R.layout.item_bunnaer, null);
                Banner banner = (Banner) MyJijiangFragment.this.view.findViewById(R.id.banner);
                int i = 0;
                while (i < MyJijiangFragment.this.jijiangshangyingInfo.getData().getBanner().size()) {
                    List localList = MyJijiangFragment.this.imagess;
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(WebAdds.YUMING);
                    localStringBuilder.append(((JijiangshangyingInfo.DataEntity.BannerEntity) MyJijiangFragment.this.jijiangshangyingInfo.getData().getBanner().get(i)).getPic());
                    localList.add(localStringBuilder.toString());
                    i += 1;
                }
                banner.setBannerStyle(1);
                banner.isAutoPlay(true);
                banner.setDelayTime(5000);
                banner.setIndicatorGravity(6);
                banner.setImages(MyJijiangFragment.this.imagess, new Banner.OnLoadImageListener() {
                    public void OnLoadImage(ImageView paramAnonymous2ImageView, Object paramAnonymous2Object) {
                        System.out.println("加载中");
                        Picasso.with(MyJijiangFragment.this.getContext()).load((String) paramAnonymous2Object).into(paramAnonymous2ImageView);
                        System.out.println("加载完");
                    }
                });
                banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    public void OnBannerClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        MyJijiangFragment.this.startActivity(new Intent(MyJijiangFragment.this.getContext(), MyShowMoveActivity.class).putExtra("flag", 0).putExtra("id", ((JijiangshangyingInfo.DataEntity.BannerEntity) MyJijiangFragment.this.jijiangshangyingInfo.getData().getBanner().get(paramAnonymous2Int - 1)).getDid()));
                    }
                });
            } catch (Exception e) {

            }
            adapter = new MyGouPiaoAdapter(MyJijiangFragment.this.getContext(), MyJijiangFragment.this.jijiangshangyingInfo, 1);
            manager = new LinearLayoutManager(MyJijiangFragment.this.getContext());
            MyJijiangFragment.this.show.setLayoutManager(MyJijiangFragment.this.manager);
            MyJijiangFragment.this.adapter.addHeaderView(MyJijiangFragment.this.view);
            MyJijiangFragment.this.show.setAdapter(MyJijiangFragment.this.adapter);
            MyJijiangFragment.this.adapter.setItemClickListener(new MyGouPiaoAdapter.OnItemClickListener() {
                public void onItemClick(int paramAnonymous2Int) {
                    MyJijiangFragment localMyJijiangFragment = MyJijiangFragment.this;
                    Intent localIntent = new Intent(MyJijiangFragment.this.getContext(), MyShowMoveActivity.class).putExtra("flag", 1);
                    List localList = MyJijiangFragment.this.jijiangshangyingInfo.getData().getList();
                    paramAnonymous2Int -= 1;
                    localMyJijiangFragment.startActivity(localIntent.putExtra("id", ((JijiangshangyingInfo.DataEntity.ListEntity) localList.get(paramAnonymous2Int)).getId()).putExtra("stime", ((JijiangshangyingInfo.DataEntity.ListEntity) MyJijiangFragment.this.jijiangshangyingInfo.getData().getList().get(paramAnonymous2Int)).getStime()));
                }
            });

            if (paramAnonymousMessage.arg1 == 1) {
                nulldata.setVisibility(View.VISIBLE);
            }
            return false;
        }
    });
    private List<String> imagess = new ArrayList();
    private JijiangshangyingInfo jijiangshangyingInfo;
    private LinearLayoutManager manager;
    @Bind({R.id.nulldata})
    RelativeLayout nulldata;
    @Bind({R.id.show})
    RecyclerView show;
    @Bind({R.id.swipeRe})
    SwipeRefreshLayout swipeRe;
    private View view;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("isshow", "2").build();
        HttpOk.GetData(WebAdds.HTTPGOUPIAO, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.i("dvtuaas",s);
                Gson gson = new Gson();
                try {
                    jijiangshangyingInfo = gson.fromJson(s, JijiangshangyingInfo.class);
                    if (MyJijiangFragment.this.jijiangshangyingInfo.getCode().equals("000")) {
                        Message message = new Message();
                        message.arg1 = 0;
                        handler.sendMessage(message);
                        return;
                    }
                    if (MyJijiangFragment.this.jijiangshangyingInfo.getCode().equals("001")) {
                        Message message = new Message();
                        message.arg1 = 1;
                        handler.sendMessage(message);
                    }
                    return;
                } catch (Exception e) {
                }
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_movelist, null);
        ButterKnife.bind(this, view);
        getData();
        this.swipeRe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                MyJijiangFragment.this.imagess.clear();
                MyJijiangFragment.this.getData();
                Log.e("sdasds", "dfdfd");
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\goupiao\MyJijiangFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
