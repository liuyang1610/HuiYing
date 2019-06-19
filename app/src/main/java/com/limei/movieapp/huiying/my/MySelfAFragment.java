package com.limei.movieapp.huiying.my;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.zhifu.MyChongZhiActivity;
import com.squareup.picasso.Picasso;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/16.
 */

/**
 * 有不同
 */

public class MySelfAFragment
        extends Fragment {
    @Bind({R.id.dingdan})
    RelativeLayout dingdan;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    @Bind({R.id.guanyu})
    RelativeLayout guanyu;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {

                String[] strings = MySelfAFragment.this.pic.split("/");
                if ((!strings[0].equals("https:")) && (!strings[0].equals("http:"))) {

                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(WebAdds.YUMING);
                    localStringBuilder.append(MySelfAFragment.this.pic);
                    Picasso.with(MySelfAFragment.this.getContext()).load(localStringBuilder.toString()).into(MySelfAFragment.this.icon);

                } else {
                    Picasso.with(MySelfAFragment.this.getContext()).load(MySelfAFragment.this.pic).into(MySelfAFragment.this.icon);
                }
                MySelfAFragment.this.name.setText(MySelfAFragment.this.names);
                return false;
            }
            Toast.makeText(MySelfAFragment.this.getContext(), "无数据", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    @Bind({R.id.huancun})
    RelativeLayout huancun;
    @Bind({R.id.icon})
    CircleTextImageView icon;
    private String mobile;
    @Bind({R.id.myinfo})
    RelativeLayout myinfo;
    @Bind({R.id.name})
    TextView name;
    private String names;
    @Bind({R.id.piaojia})
    RelativeLayout piaojia;
    private String pic;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.set})
    RelativeLayout set;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;
    @Bind({R.id.xiaoxi})
    RelativeLayout xiaoxi;
    @Bind({R.id.zhanghu})
    RelativeLayout zhanghu;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPMYINFO, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {

                        pic = jsonObject.getJSONObject("data").getString("pic");
                        names = jsonObject.getJSONObject("data").getString("name");
                        mobile = jsonObject.getJSONObject("data").getString("mobile");
                        Message message = new Message();
                        message.arg1 = 0;
                        handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        StatusBarUtil.setStatusBarColor(getActivity(), R.color.black);
        View view = paramLayoutInflater.inflate(R.layout.fragment_myself, null);
        ButterKnife.bind(this, view);
        this.strs = Unit.load(getContext()).split(",");
        this.fanhui.setVisibility(View.VISIBLE);
        this.title.setText("我的");
        getData();
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onPause() {
        super.onPause();
        getData();
    }

    public void onResume() {
        super.onResume();
        getData();
    }

    @OnClick({R.id.icon, R.id.xiaoxi, R.id.zhanghu, R.id.piaojia, R.id.dingdan, R.id.set, R.id.guanyu, R.id.myinfo, R.id.huancun})
    public void onViewClicked(View paramView) {
        switch (paramView.getId()) {
            default:

            case R.id.zhanghu:
                startActivity(new Intent(getContext(), MyChongZhiActivity.class));
                return;
            case R.id.xiaoxi:
                startActivity(new Intent(getContext(), MyNesActivity.class));
                return;
            case R.id.set:
                Unit.addDestoryActivity(getActivity(), "main");
                startActivity(new Intent(getContext(), MySetActivity.class));
                return;
            case R.id.piaojia:
                startActivity(new Intent(getContext(), MyPiaoJiaActivity.class));
                return;
            case R.id.myinfo:
                startActivity(new Intent(getContext(), MyActivity.class));
                return;
            case R.id.huancun:
                startActivity(new Intent(getContext(), MyHuanCunActivity.class));
                return;
            case R.id.guanyu:
                startActivity(new Intent(getContext(), MyGuanYuActivity.class));
                return;
            case R.id.dingdan:
                startActivity(new Intent(getContext(), MyDingDanActivity.class));
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MySelfAFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

