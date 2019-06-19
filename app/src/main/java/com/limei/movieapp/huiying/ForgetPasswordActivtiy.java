package com.limei.movieapp.huiying;

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
import com.limei.movieapp.huiying.info.ZhuceInfo;
import com.limei.movieapp.huiying.shouye.MyShouYeActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

import java.io.IOException;
import java.util.regex.Pattern;

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

public class ForgetPasswordActivtiy
        extends AppCompatActivity {
    @Bind({R.id.code})
    EditText code;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private String gender;
    @Bind({R.id.getcode})
    TextView getcode;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            ForgetPasswordActivtiy.this.getcode.setEnabled(false);
            Object localObject = new StringBuilder();
            ((StringBuilder) localObject).append(paramAnonymousMessage.arg1);
            ((StringBuilder) localObject).append("");
            Log.i("aaaa", ((StringBuilder) localObject).toString());
            localObject = ForgetPasswordActivtiy.this.getcode;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("重新发送(");
            localStringBuilder.append(paramAnonymousMessage.arg1);
            localStringBuilder.append(")");
            ((TextView) localObject).setText(localStringBuilder.toString());
            if (paramAnonymousMessage.arg1 == 0) {
                ForgetPasswordActivtiy.this.getcode.setEnabled(true);
                ForgetPasswordActivtiy.this.getcode.setText("重新发送");
            }
            return false;
        }
    });
    private Handler handlers = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                ForgetPasswordActivtiy.this.startActivity(new Intent(ForgetPasswordActivtiy.this, MyShouYeActivity.class));
                return false;
            }
            Toast.makeText(ForgetPasswordActivtiy.this, "网络错误,请重新访问", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    private String headUrl;
    private String iphone;
    @Bind({R.id.login})
    TextView login;
    private String nickname;
    private int num;
    private String openid;
    @Bind({R.id.phonenum})
    EditText phonenum;
    @Bind({R.id.title})
    TextView title;
    private String yanzhengma;
    private String type;
    private ZhuceInfo zhuceInfo;

    private void getData() {
        Object localObject = new StringBuilder();
        ((StringBuilder) localObject).append(this.openid);
        ((StringBuilder) localObject).append("   sds");
        Log.i("fdfd", ((StringBuilder) localObject).toString());
        localObject = new FormBody.Builder().add("QQappid", this.openid).add("pic", this.headUrl).add("name", this.nickname).add("mobile", this.iphone).add("type", "qq").build();
        HttpOk.GetData(WebAdds.HTTPDENGLU, (RequestBody) localObject, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                zhuceInfo = gson.fromJson(s, ZhuceInfo.class);

                if (ForgetPasswordActivtiy.this.zhuceInfo.getCode().equals("000")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getAppid());
                    stringBuilder.append(",");
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getAppsecret());
                    stringBuilder.append(",");
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getId());
                    Unit.save(stringBuilder.toString(), ForgetPasswordActivtiy.this);
                    Message message = new Message();
                    message.arg1 = 0;
                    handlers.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 1;
                handlers.sendMessage(message);
            }
        });
    }

    private void getDataWX() {
        FormBody localFormBody = new FormBody.Builder().add("WXappid", this.openid).add("pic", this.headUrl).add("name", this.nickname).add("mobile", this.iphone).add("type", "wx").build();
        HttpOk.GetData(WebAdds.HTTPDENGLU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                zhuceInfo = gson.fromJson(s, ZhuceInfo.class);

                if (ForgetPasswordActivtiy.this.zhuceInfo.getCode().equals("000")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getAppid());
                    stringBuilder.append(",");
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getAppsecret());
                    stringBuilder.append(",");
                    stringBuilder.append(ForgetPasswordActivtiy.this.zhuceInfo.getData().getId());
                    Unit.save(stringBuilder.toString(), ForgetPasswordActivtiy.this);
                    Message message = new Message();
                    message.arg1 = 0;
                    handlers.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 1;
                handlers.sendMessage(message);
            }
        });
    }

    private void getDataYanZhengMa(String paramString1, String paramString2) {
        Log.i("iphoness", paramString2);
        FormBody formBody = new FormBody.Builder().add("yzm", paramString1).add("tel", paramString2).build();
        HttpOk.GetData(WebAdds.HTTPDUANXIN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                yanzhengma = paramAnonymousResponse.body().string();
                Log.i("sdfds", yanzhengma);
            }
        });
    }

    public static boolean isTelPhoneNumber(String paramString) {
        if ((paramString != null) && (paramString.length() == 11)) {
            return Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$").matcher(paramString).matches();
        }
        return false;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_forget_password_activtiy);
        ButterKnife.bind(this);
        this.headUrl = getIntent().getStringExtra("headUrl");
        this.gender = getIntent().getStringExtra("gender");
        this.nickname = getIntent().getStringExtra("nickname");
        this.openid = getIntent().getStringExtra("openid");
        this.type = getIntent().getStringExtra("type");
    }

    @OnClick({R.id.getcode, R.id.login, R.id.fanhui})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.getcode) {
                if (i != R.id.login) {
                    return;
                }

                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(this.yanzhengma);
                localStringBuilder.append("");
                if (code.getText().toString().trim().equals(localStringBuilder.toString())) {
                    if (this.type.equals("qq")) {
                        getData();
                        return;
                    }
                    getDataWX();
                    return;
                }
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            this.num = ((int) ((Math.random() * 9.0D + 1.0D) * 100000.0D));
            this.phonenum.setEnabled(false);
            this.iphone = this.phonenum.getText().toString().trim();
            if (isTelPhoneNumber(this.iphone)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.num);
                stringBuilder.append("");
                getDataYanZhengMa(stringBuilder.toString(), this.iphone);
                new Thread(new Runnable() {
                    public void run() {
                        int i = 60;
                        while (i >= 0) {
                            try {
                                Thread.sleep(1000L);
                                Message localMessage = new Message();
                                localMessage.arg1 = i;
                                ForgetPasswordActivtiy.this.handler.sendMessage(localMessage);
                            } catch (InterruptedException localInterruptedException) {
                                localInterruptedException.printStackTrace();
                            }
                            i -= 1;
                        }
                    }
                }).start();
                return;
            }
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\ForgetPasswordActivtiy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */