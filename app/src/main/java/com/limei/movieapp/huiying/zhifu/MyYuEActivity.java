package com.limei.movieapp.huiying.zhifu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.GoupiaoInfo;
import com.limei.movieapp.huiying.unit.AlertDialog;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

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
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 有不同
 */


public class MyYuEActivity
        extends AppCompatActivity {
    private int a = 1;
    @Bind({R.id.cahngci})
    TextView cahngci;
    @Bind({R.id.chongzhi})
    TextView chongzhi;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private GoupiaoInfo goupiaoInfo;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg2 == 0) {
                MyYuEActivity.this.name.setText(MyYuEActivity.this.goupiaoInfo.getData().getTitle());
                MyYuEActivity.this.sub.setText(MyYuEActivity.this.goupiaoInfo.getData().getSub());
                MyYuEActivity.this.cahngci.setText(MyYuEActivity.this.goupiaoInfo.getData().getDescription());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(MyYuEActivity.this.goupiaoInfo.getData().getXtime());
                stringBuilder.append("000");
                Date date = new Date(Long.parseLong(stringBuilder.toString()));
                String s = new SimpleDateFormat("yyyy-MM-dd").format(date);

                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(s);
                localStringBuilder.append("下线");
                MyYuEActivity.this.namemall.setText(localStringBuilder.toString());

                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("￥");
                stringBuilder1.append(MyYuEActivity.this.goupiaoInfo.getData().getPrice());
                MyYuEActivity.this.jine.setText(stringBuilder1.toString());


                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("余额");
                stringBuilder2.append(MyYuEActivity.this.goupiaoInfo.getData().getBalance());
                stringBuilder2.append("元");
                MyYuEActivity.this.yue.setText(stringBuilder2.toString());
                if (Integer.parseInt(MyYuEActivity.this.goupiaoInfo.getData().getBalance()) < Integer.parseInt(MyYuEActivity.this.goupiaoInfo.getData().getPrice())) {
                    MyYuEActivity.this.shuoming.setVisibility(View.VISIBLE);
                    isgou = true;
                    return false;
                }
            } else {
                if (paramAnonymousMessage.arg2 == 1) {
                    return false;
                }
                if (paramAnonymousMessage.arg2 == 2) {
                    Toast.makeText(MyYuEActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                    return false;
                }
                int i = paramAnonymousMessage.arg2;
            }
            return false;
        }
    });
    private String id;
    private boolean isgou = true;
    @Bind({R.id.jia})
    TextView jia;
    @Bind({R.id.jian})
    TextView jian;
    @Bind({R.id.jine})
    TextView jine;
    @Bind({R.id.lijizhifu})
    TextView lijizhifu;
    @Bind({R.id.name})
    TextView name;
    @Bind({R.id.namemall})
    TextView namemall;
    @Bind({R.id.num})
    EditText num;
    private int nums;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.shuoming})
    TextView shuoming;
    private String[] strs;
    @Bind({R.id.sub})
    TextView sub;
    @Bind({R.id.title})
    TextView title;
    @Bind({R.id.youxiaoqi})
    TextView youxiaoqi;
    @Bind({R.id.yue})
    TextView yue;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.id).add("uid", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPGOUMAI, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                goupiaoInfo = gson.fromJson(s, GoupiaoInfo.class);

                if (MyYuEActivity.this.goupiaoInfo.getCode().equals("000")) {
                    Message message = new Message();
                    message.arg2 = 0;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg2 = 1;
                handler.sendMessage(message);
            }
        });
    }

    private void getDataLiJiGou() {
        Object localObject = new StringBuilder();
        ((StringBuilder) localObject).append("onFailure: ");
        ((StringBuilder) localObject).append(this.id);
        ((StringBuilder) localObject).append("   ");
        ((StringBuilder) localObject).append(this.strs[2]);
        ((StringBuilder) localObject).append("  ");
        ((StringBuilder) localObject).append(this.a);
        Log.d("ggdsfs", ((StringBuilder) localObject).toString());
        localObject = new FormBody.Builder().add("id", this.id).add("uid", this.strs[2]);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(this.a);
        localStringBuilder.append("");
        localObject = ((FormBody.Builder) localObject).add("num", localStringBuilder.toString()).build();
        HttpOk.GetData(WebAdds.HTTPLIJIGOUMAI, (RequestBody) localObject, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    if (new JSONObject(s).getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg2 = 2;
                        handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg2 = 3;
                    handler.sendMessage(message);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_yu_e);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.title.setText("购票");
        this.id = getIntent().getStringExtra("id");
        getData();
    }

    @OnClick({R.id.fanhui, R.id.jian, R.id.jia, R.id.chongzhi, R.id.lijizhifu})
    public void onViewClicked(View paramView) {
        StringBuilder localStringBuilder;
        switch (paramView.getId()) {
            default:

            case R.id.lijizhifu:
                new AlertDialog(this).builder().setMsg("提示").settitlContent("是否立即支付").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        MyYuEActivity.this.getDataLiJiGou();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                    }
                }).show();
                return;
            case R.id.jian:
                this.a = Integer.parseInt(this.num.getText().toString().trim());
                if (this.a > 1) {
                    this.a -= 1;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(this.a);
                    localStringBuilder.append("");
                    num.setText(localStringBuilder.toString());
                    return;
                }
                break;
            case R.id.jia:
                this.a = Integer.parseInt(this.num.getText().toString().trim());
                this.a += 1;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(this.a);
                localStringBuilder.append("");
                num.setText(localStringBuilder.toString());
                return;
            case R.id.fanhui:
                finish();
                return;
            case R.id.chongzhi:
                startActivityForResult(new Intent(this, MyChongZhiActivity.class), 1);
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\zhifu\MyYuEActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
