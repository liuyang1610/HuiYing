package com.limei.movieapp.huiying.my;

import android.content.Intent;
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
import com.limei.movieapp.huiying.info.MyXiaoXiInfo;
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

public class MyNesActivity
        extends AppCompatActivity {
    private MyNesAdapter adapter;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                manager = new LinearLayoutManager(MyNesActivity.this);
                adapter = new MyNesAdapter(MyNesActivity.this, MyNesActivity.this.myXiaoXiInfo);
                MyNesActivity.this.newsshow.setLayoutManager(MyNesActivity.this.manager);
                MyNesActivity.this.newsshow.setAdapter(MyNesActivity.this.adapter);
                MyNesActivity.this.adapter.setOnItemClickListener(new MyNesAdapter.OnItemClickListener() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        MyNesActivity.this.startActivity(new Intent(MyNesActivity.this, MyXiaoXiXiangActivity.class).putExtra("id", ((MyXiaoXiInfo.DataEntity) MyNesActivity.this.myXiaoXiInfo.getData().get(paramAnonymous2Int)).getId()));
                    }
                });
                return false;
            }
            Toast.makeText(MyNesActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    private LinearLayoutManager manager;
    private MyXiaoXiInfo myXiaoXiInfo;
    @Bind({R.id.newsshow})
    RecyclerView newsshow;
    @Bind({R.id.rightss})
    TextView rightss;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPXIAOXI, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                myXiaoXiInfo = gson.fromJson(s, MyXiaoXiInfo.class);

                if (MyNesActivity.this.myXiaoXiInfo.getCode().equals("000")) {
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
        setContentView(R.layout.activity_my_nes);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.title.setText("消息");
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


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyNesActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */


