package com.limei.movieapp.huiying.news;

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
import android.widget.TextView;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.NewInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/17.
 */

/**
 * 有不同
 */

public class MyNewFragment
        extends Fragment {
    private MyNewsAdapter adapter;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if ((paramAnonymousMessage.arg1 == 0)) {

                if (MyNewFragment.this.swipeRe.isRefreshing()) {
                    try {
                        MyNewFragment.this.adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
                manager = new LinearLayoutManager(MyNewFragment.this.getContext());
                adapter = new MyNewsAdapter(MyNewFragment.this.getContext(), MyNewFragment.this.newInfo);
                MyNewFragment.this.newsshow.setLayoutManager(MyNewFragment.this.manager);
                MyNewFragment.this.newsshow.setAdapter(MyNewFragment.this.adapter);
                MyNewFragment.this.swipeRe.setRefreshing(false);
                MyNewFragment.this.nulldata.setVisibility(View.GONE);


                MyNewFragment.this.adapter.setItemClickListener(new MyNewsAdapter.OnItemClickListener() {
                    public void onItemClick(int paramAnonymous2Int) {
                        MyNewFragment.this.startActivity(new Intent(MyNewFragment.this.getContext(), MyNewsContentActivity.class).putExtra("id", ((NewInfo.DataEntity) MyNewFragment.this.newInfo.getData().get(paramAnonymous2Int)).getId()));
                    }
                });
            } else if (paramAnonymousMessage.arg1 == 1) {
                if (MyNewFragment.this.swipeRe.isRefreshing()) {
                    try {
                        MyNewFragment.this.adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
                MyNewFragment.this.nulldata.setVisibility(View.VISIBLE);
            }
            return false;
        }
    });
    private LinearLayoutManager manager;
    private NewInfo newInfo;
    @Bind({R.id.newsshow})
    RecyclerView newsshow;
    @Bind({R.id.nulldata})
    RelativeLayout nulldata;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.swipeRe})
    SwipeRefreshLayout swipeRe;
    @Bind({R.id.title})
    TextView title;
    private List<Integer> typeList = new ArrayList();

    private void getDataLiJiGou() {
        FormBody localFormBody = new FormBody.Builder().build();
        HttpOk.GetData(WebAdds.HTTPNEWSS, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.d("kananan", "onFailure: 错误");
                Gson gson = new Gson();
                newInfo = gson.fromJson(s, NewInfo.class);
                if (MyNewFragment.this.newInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                    return;
                }
                if (MyNewFragment.this.newInfo.getCode().equals("002")) {
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 2;
                MyNewFragment.this.handler.sendMessage(message);
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        this.title.setText("新闻");
        this.fanhui.setVisibility(View.GONE);
        getDataLiJiGou();
        this.swipeRe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                MyNewFragment.this.getDataLiJiGou();
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        paramView.getId();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\news\MyNewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

