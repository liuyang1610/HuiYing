package com.limei.movieapp.huiying;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.application.MyApplication;
import com.limei.movieapp.huiying.info.ZhuceInfo;
import com.limei.movieapp.huiying.shouye.MyShouYeActivity;
import com.limei.movieapp.huiying.unit.BaseActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.tencent.connect.UserInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
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
public class MainActivity
        extends BaseActivity {
    @Bind({R.id.code})
    EditText code;
    private String expires;
    private String gender;
    @Bind({R.id.getcode})
    TextView getcode;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            MainActivity.this.getcode.setEnabled(false);
            Object localObject = new StringBuilder();
            ((StringBuilder) localObject).append(paramAnonymousMessage.arg1);
            ((StringBuilder) localObject).append("");
            Log.i("aaaa", ((StringBuilder) localObject).toString());
            localObject = MainActivity.this.getcode;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("重新发送(");
            localStringBuilder.append(paramAnonymousMessage.arg1);
            localStringBuilder.append(")");
            ((TextView) localObject).setText(localStringBuilder.toString());
            if (paramAnonymousMessage.arg1 == 0) {
                MainActivity.this.getcode.setEnabled(true);
                MainActivity.this.getcode.setText("重新发送");
            }
            return false;
        }
    });
    private Handler handlers = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MyShouYeActivity.class));
                return false;
            }
            if (paramAnonymousMessage.arg1 == 1) {
                Toast.makeText(MainActivity.this, "网络错误,请重新访问", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 2) {
                Log.d("kananan", "22222222222222222");
                MainActivity.this.getDataLogin();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 3) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, ForgetPasswordActivtiy.class).putExtra("headUrl", MainActivity.this.headUrl).putExtra("gender", MainActivity.this.gender).putExtra("nickname", MainActivity.this.nickname).putExtra("openid", MainActivity.this.openid_qq).putExtra("type", "qq"));
                return false;
            }
            if (paramAnonymousMessage.arg1 == 4) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MyShouYeActivity.class));
                return false;
            }
            if (paramAnonymousMessage.arg1 == 5) {
                Toast.makeText(MainActivity.this, "网络错误,请重新访问", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    private String headUrl;
    private String iphone;
    private String yanzhma;
    @Bind({R.id.login})
    TextView login;
    private LogInListener mListener;
    private Tencent mTencent;
    private String nickname;
    private int num;
    private String openid_qq = "";
    @Bind({R.id.phonenum})
    EditText phonenum;
    @Bind({R.id.qq})
    ImageView qq;
    @Bind({R.id.shoujihao})
    ImageView shoujihao;
    private String token;
    @Bind({R.id.weixin})
    ImageView weixin;
    @Bind({R.id.yanzhengma})
    ImageView yanzhengma;
    private ZhuceInfo zhuceInfo;

    private void getData() {
        this.iphone = this.phonenum.getText().toString().trim();
        FormBody localFormBody = new FormBody.Builder().add("mobile", this.iphone).add("type", "mobile").build();
        HttpOk.GetData(WebAdds.HTTPDENGLU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.i("vbcdss", s);
                Gson gson = new Gson();
                zhuceInfo = gson.fromJson(s, ZhuceInfo.class);

                if (MainActivity.this.zhuceInfo.getCode().equals("000")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getAppid());
                    stringBuilder.append(",");
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getAppsecret());
                    stringBuilder.append(",");
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getId());
                    Unit.save(stringBuilder.toString(), MainActivity.this);
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

    private void getDataIsQQ(String paramString) {
        FormBody formBody = new FormBody.Builder().add("QQappid", paramString).build();
        HttpOk.GetData(WebAdds.HTTPISQQ, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    if (jsonObject.getString("code").equals("000")) {
                        Log.d("kananan", "22222222222222222");
                        iphone = jsonObject.getJSONObject("data").getString("mobile");
                        Message message = new Message();
                        message.arg1 = 2;
                        handlers.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg1 = 3;
                        handlers.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataLogin() {
        FormBody localFormBody = new FormBody.Builder().add("QQappid", this.openid_qq).add("pic", this.headUrl).add("name", this.nickname).add("mobile", this.iphone).add("type", "qq").build();
        HttpOk.GetData(WebAdds.HTTPDENGLU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                zhuceInfo = gson.fromJson(s, ZhuceInfo.class);

                if (MainActivity.this.zhuceInfo.getCode().equals("000")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getAppid());
                    stringBuilder.append(",");
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getAppsecret());
                    stringBuilder.append(",");
                    stringBuilder.append(MainActivity.this.zhuceInfo.getData().getId());
                    Unit.save(paramAnonymousCall.toString(), MainActivity.this);
                    Message message = new Message();
                    message.arg1 = 4;
                    MainActivity.this.handlers.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg1 = 5;
                MainActivity.this.handlers.sendMessage(message);
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
                yanzhma = paramAnonymousResponse.body().string();

                new Gson();
            }
        });
    }

    private void getUserInfo() {
        new UserInfo(this, this.mTencent.getQQToken()).getUserInfo(new IUiListener() {
            public void onCancel() {
                Log.e("GET_QQ_INFO_CANCEL", "获取qq用户信息取消");
                Toast.makeText(MainActivity.this, "获取qq用户信息取消", Toast.LENGTH_SHORT).show();
            }

            public void onComplete(Object paramAnonymousObject) {
                JSONObject localJSONObject = (JSONObject) paramAnonymousObject;
                Log.i("QQyonghuxixni", localJSONObject.toString());
                Message localMessage = new Message();
                localMessage.what = 0;
                try {
                    localMessage.obj = localJSONObject.getString("nickname");
                    headUrl = ((JSONObject) paramAnonymousObject).getString("figureurl_qq_2");
                    gender = ((JSONObject) paramAnonymousObject).getString("figureurl_qq_2");
                    nickname = ((JSONObject) paramAnonymousObject).getString("figureurl_qq_2");

                    MainActivity.this.getDataIsQQ(MainActivity.this.openid_qq);
                    return;
                } catch (JSONException e) {
                    ((JSONException) paramAnonymousObject).printStackTrace();
                }
            }

            public void onError(UiError paramAnonymousUiError) {
                Log.e("GET_QQ_INFO_ERROR", "获取qq用户信息错误");
                Toast.makeText(MainActivity.this, "获取qq用户信息错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initOpenidAndToken(JSONObject paramJSONObject) {
        try {
            this.openid_qq = paramJSONObject.getString("openid");
            this.token = paramJSONObject.getString("access_token");
            this.expires = paramJSONObject.getString("expires_in");
            this.mTencent.setAccessToken(this.token, this.expires);
            this.mTencent.setOpenId(this.openid_qq);

            return;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTelPhoneNumber(String paramString) {
        if ((paramString != null) && (paramString.length() == 11)) {
            return Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$").matcher(paramString).matches();
        }
        return false;
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        Tencent.onActivityResultData(paramInt1, paramInt2, paramIntent, this.mListener);
    }

    @RequiresApi(api = 21)
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.mTencent = Tencent.createInstance("1107744041", getApplicationContext());
        this.mListener = new LogInListener();
    }

    @OnClick({R.id.getcode, R.id.login, R.id.weixin, R.id.qq})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        StringBuilder localStringBuilder;
        if (i != R.id.getcode) {
            if (i != R.id.login) {
                if (i != R.id.qq) {
                    if (i != R.id.weixin) {
                        return;
                    }
//                    if (!Unit.isWeixinAvilible(this)) {
//                        Toast.makeText(this, "您还未安装微信客户端", 0).show();
//                        return;
//                    }
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "diandi_wx_login";
                    MyApplication.mWxApi.sendReq(req);
                    return;
                }
                this.mTencent.login(this, "all", this.mListener);
                return;
            }


            if (code.getText().toString().trim().equals(num + "")) {
                getData();
                return;
            }
            Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        this.num = ((int) ((Math.random() * 9.0D + 1.0D) * 100000.0D));
        iphone = this.phonenum.getText().toString().trim();
        if (isTelPhoneNumber(iphone)) {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(this.num);
            localStringBuilder.append("");
            getDataYanZhengMa(localStringBuilder.toString(), iphone);
            new Thread(new Runnable() {
                public void run() {
                    int i = 60;
                    while (i >= 0) {
                        try {
                            Thread.sleep(1000L);
                            Message localMessage = new Message();
                            localMessage.arg1 = i;
                            MainActivity.this.handler.sendMessage(localMessage);
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
    }

    private class LogInListener
            implements IUiListener {
        private LogInListener() {
        }

        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消！", Toast.LENGTH_SHORT).show();
        }

        public void onComplete(Object paramObject) {
            Toast.makeText(MainActivity.this, "授权成功！", Toast.LENGTH_SHORT).show();
            PrintStream localPrintStream = System.out;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("o.toString() ------------------------->        ");
            localStringBuilder.append(paramObject.toString());
            localPrintStream.println(localStringBuilder.toString());
            paramObject = (JSONObject) paramObject;
            MainActivity.this.initOpenidAndToken((JSONObject) paramObject);
            MainActivity.this.getUserInfo();
        }

        public void onError(UiError paramUiError) {
            Toast.makeText(MainActivity.this, "授权出错！", Toast.LENGTH_SHORT).show();
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

