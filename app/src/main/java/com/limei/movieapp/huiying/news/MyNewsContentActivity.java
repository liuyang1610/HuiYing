package com.limei.movieapp.huiying.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.NesContentInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.zzhoujay.richtext.RichText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class MyNewsContentActivity
        extends AppCompatActivity {
    @Bind({R.id.biaoti})
    TextView biaoti;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {

            if (paramAnonymousMessage.arg1 == 0) {
                MyNewsContentActivity.this.biaoti.setText(MyNewsContentActivity.this.nesContentInfo.getData().getTitle());
                String news = MyNewsContentActivity.this.nesContentInfo.getData().getContent().replaceAll("src=\\\"", "src=\\\"http://hy.shiduweb.com");
                Log.i("sds", news);
                RichText.from(news).into(MyNewsContentActivity.this.xiangqing);
                if (MyNewsContentActivity.this.nesContentInfo.getData().getIsfabulous().equals("1")) {
                    MyNewsContentActivity.this.znatu.setBackgroundResource(R.drawable.dz1);
                } else {
                    MyNewsContentActivity.this.znatu.setBackgroundResource(R.drawable.dz);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(MyNewsContentActivity.this.nesContentInfo.getData().getCtime());
                stringBuilder.append("000");
                Date date = new Date(Long.parseLong(stringBuilder.toString()));
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                MyNewsContentActivity.this.timess.setText(time);

            } else if (paramAnonymousMessage.arg1 == 2) {
                if (paramAnonymousMessage.obj.equals("点赞成功")) {
                    MyNewsContentActivity.this.znatu.setBackgroundResource(R.drawable.dz1);
                } else {
                    MyNewsContentActivity.this.znatu.setBackgroundResource(R.drawable.dz);
                }
            } else if (paramAnonymousMessage.arg1 != 3) {
                int i = paramAnonymousMessage.arg1;
            }
            return false;
        }
    });
    @Bind({R.id.head})
    RelativeLayout head;
    private String id;
    private NesContentInfo nesContentInfo;
    @Bind({R.id.pinglun})
    EditText pinglun;
    @Bind({R.id.plnum})
    TextView plnum;
    private String[] strs;
    @Bind({R.id.times})
    TextView times;
    @Bind({R.id.timess})
    TextView timess;
    @Bind({R.id.tubiao})
    ImageView tubiao;
    @Bind({R.id.xiangqing})
    TextView xiangqing;
    @Bind({R.id.zan})
    RelativeLayout zan;
    @Bind({R.id.znatu})
    ImageView znatu;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.id).add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPNEWSSCONTENT, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                nesContentInfo = gson.fromJson(s,NesContentInfo.class);

                if (MyNewsContentActivity.this.nesContentInfo.getCode().equals("000")) {
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

    private void getDataDianZan() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.id).add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPDIANZAN, localFormBody, new Callback() {
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
                        Message message = new Message();
                        message.arg1 = 2;
                        message.obj = jsonObject.getString("message");
                        MyNewsContentActivity.this.handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        return;
                    }
                    jsonObject.getString("code").equals("003");
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.StatusBarLightMode(this, 2);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_news_content);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.id = getIntent().getStringExtra("id");
        getData();
    }

    @OnClick({R.id.fanhui, R.id.tubiao, R.id.plnum, R.id.zan, R.id.times})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.plnum) {
                if (i != R.id.tubiao) {
                    if (i != R.id.zan) {
                        return;
                    }
                    getDataDianZan();
                    return;
                }
                startActivity(new Intent(this, MyTalkActivity.class));
                return;
            }
            startActivity(new Intent(this, MyTalkActivity.class));
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\news\MyNewsContentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

