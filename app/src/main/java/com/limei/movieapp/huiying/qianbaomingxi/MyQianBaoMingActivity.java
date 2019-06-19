package com.limei.movieapp.huiying.qianbaomingxi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyMingXiInfo;
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


public class MyQianBaoMingActivity
        extends AppCompatActivity {
    private MyMingXiAdapter adapter;
    @Bind({R.id.chongzhishow})
    RecyclerView chongzhishow;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                manager = new LinearLayoutManager(MyQianBaoMingActivity.this);
                adapter = new MyMingXiAdapter(MyQianBaoMingActivity.this, MyQianBaoMingActivity.this.myMingXiInfo);
                MyQianBaoMingActivity.this.chongzhishow.setLayoutManager(MyQianBaoMingActivity.this.manager);
                MyQianBaoMingActivity.this.chongzhishow.setAdapter(MyQianBaoMingActivity.this.adapter);
                return false;
            }
            Toast.makeText(MyQianBaoMingActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    private LinearLayoutManager manager;
    private MyMingXiInfo myMingXiInfo;
    @Bind({R.id.rightss})
    TextView rightss;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPQIANBAOMINGXI, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                myMingXiInfo = gson.fromJson(s, MyMingXiInfo.class);

                if (MyQianBaoMingActivity.this.myMingXiInfo.getCode().equals("000")) {
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

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_qian_bao_ming);
        ButterKnife.bind(this);
        this.title.setText("充值明细");
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


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\qianbaomingxi\MyQianBaoMingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */