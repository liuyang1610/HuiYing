package com.limei.movieapp.huiying.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyZengPiaoInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 有不同
 */


public class MyPiaoJiaActivity
        extends AppCompatActivity {
    private MyPiaoJiaAdapter adapter;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                MyPiaoJiaActivity.this.nulldata.setVisibility(View.GONE);
                manager = new LinearLayoutManager(MyPiaoJiaActivity.this);
                adapter = new MyPiaoJiaAdapter(MyPiaoJiaActivity.this, MyPiaoJiaActivity.this.myZengPiaoInfo);
                MyPiaoJiaActivity.this.piaojiashow.setLayoutManager(MyPiaoJiaActivity.this.manager);
                MyPiaoJiaActivity.this.piaojiashow.setAdapter(MyPiaoJiaActivity.this.adapter);
                return false;
            }
            if (paramAnonymousMessage.arg1 == 1) {
                MyPiaoJiaActivity.this.nulldata.setVisibility(View.VISIBLE);
            }
            return false;
        }
    });
    private LinearLayoutManager manager;
    private MyZengPiaoInfo myZengPiaoInfo;
    @Bind({R.id.nulldata})
    RelativeLayout nulldata;
    @Bind({R.id.piaojiashow})
    RecyclerView piaojiashow;
    @Bind({R.id.rightss})
    TextView rightss;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPZENGPIAOJILU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.d("kananan", "onFailure: "+s);
                Gson gson = new Gson();
                myZengPiaoInfo = gson.fromJson(s, MyZengPiaoInfo.class);
                if (MyPiaoJiaActivity.this.myZengPiaoInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg1 = 0;
                    MyPiaoJiaActivity.this.handler.sendMessage(message);
                    return;
                }
                if (MyPiaoJiaActivity.this.myZengPiaoInfo.getCode().equals("002")) {
                    Message message = new Message();
                    message.arg1 = 1;
                    MyPiaoJiaActivity.this.handler.sendMessage(message);
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_piao_jia);
        ButterKnife.bind(this);
        this.title.setText("票夹");
        this.strs = Unit.load(this).split(",");
        getData();
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        if (paramView.getId() != R.id.fanhui) {
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyPiaoJiaActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
