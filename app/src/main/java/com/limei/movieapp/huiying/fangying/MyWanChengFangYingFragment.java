package com.limei.movieapp.huiying.fangying;

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
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyFangYingZhengInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/15.
 */

/***
 * 有不同
 */

public class MyWanChengFangYingFragment
        extends Fragment {
    private MyFangYingAdapter adapter;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                if (MyWanChengFangYingFragment.this.swipeRefreshLayout.isRefreshing()) {
                    MyWanChengFangYingFragment.this.adapter.notifyDataSetChanged();
                    MyWanChengFangYingFragment.this.swipeRefreshLayout.setRefreshing(false);
                }
                MyWanChengFangYingFragment.this.nulldata.setVisibility(View.GONE);
                manager = new LinearLayoutManager(MyWanChengFangYingFragment.this.getContext());
                adapter = new MyFangYingAdapter(MyWanChengFangYingFragment.this.getContext(), MyWanChengFangYingFragment.this.myFangYingZhengInfo);
                MyWanChengFangYingFragment.this.shoere.setLayoutManager(MyWanChengFangYingFragment.this.manager);
                MyWanChengFangYingFragment.this.shoere.setAdapter(MyWanChengFangYingFragment.this.adapter);
                return false;
            }
            if (MyWanChengFangYingFragment.this.swipeRefreshLayout.isRefreshing()) {

            }
            try {
                MyWanChengFangYingFragment.this.adapter.notifyDataSetChanged();
                MyWanChengFangYingFragment.this.swipeRefreshLayout.setRefreshing(false);
                MyWanChengFangYingFragment.this.nulldata.setVisibility(View.VISIBLE);
                return false;
            } catch (Exception e) {

            }
            return false;
        }
    });
    private LinearLayoutManager manager;
    private MyFangYingZhengInfo myFangYingZhengInfo;
    @Bind({R.id.nulldata})
    RelativeLayout nulldata;
    @Bind({R.id.shoere})
    RecyclerView shoere;
    private String[] strs;
    @Bind({R.id.swipeRe})
    SwipeRefreshLayout swipeRefreshLayout;

    private void getDataWanCheng() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPWANCHENGFANGYING, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onFailure: ");
                stringBuilder.append(paramAnonymousCall);
                Log.d("hahahah", paramAnonymousResponse.toString());
                Gson gson = new Gson();
                myFangYingZhengInfo = gson.fromJson(s, MyFangYingZhengInfo.class);

                if (MyWanChengFangYingFragment.this.myFangYingZhengInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 1;
                handler.sendMessage(message);
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_zhengzaifang, null);
        ButterKnife.bind(this, view);
        this.strs = Unit.load(getContext()).split(",");
        getDataWanCheng();
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                MyWanChengFangYingFragment.this.getDataWanCheng();
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


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\fangying\MyWanChengFangYingFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */