/**
 * 添加
 */
package com.limei.movieapp.huiying.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.ForgetPasswordActivtiy;
import com.limei.movieapp.huiying.application.MyApplication;
import com.limei.movieapp.huiying.info.ZhuceInfo;
import com.limei.movieapp.huiying.info.ZhuceInfo.DataEntity;
import com.limei.movieapp.huiying.shouye.MyShouYeActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.GetRequest;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity
        extends Activity
        implements IWXAPIEventHandler {
    public static String APP_ID = "wxebe5fd0313de8f4d".trim();
    private static String path = "/sdcard/qq/";
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg2 == 0) {
                Log.d("kananan", "22222222222222222");
                WXEntryActivity.this.getDataLogin();
            }
            if (paramAnonymousMessage.arg2 == 1) {
                WXEntryActivity.this.startActivity(new Intent(WXEntryActivity.this, ForgetPasswordActivtiy.class).putExtra("headUrl", WXEntryActivity.this.headimgurl).putExtra("gender", WXEntryActivity.this.sex).putExtra("nickname", WXEntryActivity.this.nickname).putExtra("openid", WXEntryActivity.this.openids).putExtra("type", "wx"));
            }
            if (paramAnonymousMessage.arg2 == 2) {
                WXEntryActivity.this.startActivity(new Intent(WXEntryActivity.this, MyShouYeActivity.class));
            }
            if (paramAnonymousMessage.arg2 == 3) {
                Toast.makeText(WXEntryActivity.this, "网络错误,请重新访问", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    String headimgurl = "";
    private String iphone = "";
    String nickname = "";
    String openids = "";
    private String sex;
    private String[] strs;
    private ZhuceInfo zhuceInfo;

    private void getDataIsWeiXin(String paramString) {
        FormBody formBody = new FormBody.Builder().add("WXappid", paramString).build();
        HttpOk.GetData(WebAdds.HTTPISWX, formBody, new okhttp3.Callback() {
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
                        message.arg2 = 0;
                        handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg2 = 1;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataLogin() {
        FormBody localFormBody = new FormBody.Builder().add("WXappid", this.openids).add("pic", this.headimgurl).add("name", this.nickname).add("mobile", this.iphone).add("type", "wx").build();
        HttpOk.GetData(WebAdds.HTTPDENGLU, localFormBody, new okhttp3.Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                zhuceInfo = gson.fromJson(s, ZhuceInfo.class);

                if (WXEntryActivity.this.zhuceInfo.getCode().equals("000")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(WXEntryActivity.this.zhuceInfo.getData().getAppid());
                    stringBuilder.append(",");
                    stringBuilder.append(WXEntryActivity.this.zhuceInfo.getData().getAppsecret());
                    stringBuilder.append(",");
                    stringBuilder.append(WXEntryActivity.this.zhuceInfo.getData().getId());
                    Unit.save(stringBuilder.toString(), WXEntryActivity.this);
                    Message message = new Message();
                    message.arg2 = 2;
                    handler.sendMessage(message);
                    return;
                }
                Message message = new Message();
                message.arg2 = 3;
                handler.sendMessage(message);
            }
        });
    }

    private void getDataQuXiao(String paramString1, String paramString2, String paramString3) {
        FormBody formBody = new FormBody.Builder().add("ticketnumber", paramString3).add("uid", paramString2).add("id", paramString1).build();
        HttpOk.GetData(WebAdds.HTTPQUXIAO, formBody, new okhttp3.Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onFailure: ");
                stringBuilder.append(s);
                Log.d("kananan", stringBuilder.toString());
                new Gson();
            }
        });
    }

    private void getToken(String paramString) {

        OkGo.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=f3d59f3c6ad41447c2a7f9bdf3570064&code=" + paramString + "&grant_type=authorization_code")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        String access_token = "";//接口调用凭证。
                        String expires_in = "";//接口调用凭证超时时间，单位（秒）。
                        String refresh_token = "";//用户刷新access_token。
                        String openid = "";//授权用户唯一标识。
                        String scope = "";//用户授权的作用域，使用逗号（,）分隔
                        String unionid = "";//当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
                        Log.i("sfdsf", s);
                        try {
                            JSONObject job = new JSONObject(s);
                            access_token = job.getString("access_token");
                            expires_in = job.getString("expires_in");
                            refresh_token = job.getString("refresh_token");
                            openid = job.getString("openid");
                            scope = job.getString("scope");
                            unionid = job.getString("unionid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // 判断openid是否为空
                        if (!openid.equals("")) {
                            Log.i("wxentry", access_token + "---" + openid + "---" + expires_in + "---" + refresh_token + "---" + scope + "---" + unionid);

                            //这个位置调用后台给的登录接口，别忘了登录成功之后finish()当前activity，不然会停留在空白页
                            //传参给后台，获取返回的token，UID并保存
                            setLogin(access_token, openid);
                        } else {
                            Log.i("wxentry", "检查appsecret码是否重置");
                            //销毁界面
                            finish();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("wxentry", "失败&&&&&&&&&&&&&&");
                        WXEntryActivity.this.finish();
                    }
                });

    }

    private void setLogin(String paramString1, String paramString2) {
        HttpOk.GetData("https://api.weixin.qq.com/sns/userinfo", new FormBody.Builder().add("access_token", paramString1).add("openid", paramString2).build(), new okhttp3.Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    WXEntryActivity.this.openids = jsonObject.getString("openid");
                    WXEntryActivity.this.nickname = jsonObject.getString("nickname");
                    WXEntryActivity.this.headimgurl = jsonObject.getString("headimgurl");
                    if (jsonObject.getInt("sex") == 1) {
                        sex = "男";
                    } else {
                        sex = "女";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WXEntryActivity.this.getDataIsWeiXin(WXEntryActivity.this.openids);
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Log.i("sfdsf", "w**************dfdsfds");
        this.strs = Unit.load(this).split(",");
        MyApplication.mWxApi.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
        setIntent(paramIntent);
        MyApplication.mWxApi.handleIntent(paramIntent, this);
    }

    public void onReq(BaseReq paramBaseReq) {
    }

    public void onResp(BaseResp paramBaseResp) {
        Log.i("sfdsf", "w**************dfdsfds111111111");
        int i;
        if (paramBaseResp.getType() == 2) {
            Log.i("sfdsf", "w**************dfdsfds222222222");
            i = paramBaseResp.errCode;
            if (i != -4) {
                if (i == -2) {
                    getDataQuXiao(MyApplication.id, MyApplication.uid, MyApplication.ticketnumber);
                    Toast.makeText(this, "分享取消!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "用户拒绝取消!", Toast.LENGTH_SHORT).show();
            }
            finish();
            return;
        }
        if (paramBaseResp.getType() == 1) {
            Log.i("sfdsf", "w**************dfdsfds333333333333   " + paramBaseResp.errCode);
            switch (paramBaseResp.errCode) {
                //如果errcode为零，表示用户同意授权
                case BaseResp.ErrCode.ERR_OK:
                    // 得到所需的code
                    //此处报错检查下载的sdk是否正确

                    String code = ((SendAuth.Resp) paramBaseResp).code;

                    Log.i("sfdsf", "code = *****************************S" + code);
                    // 获取code之后携带参数获取用户信息
                    getToken(code);
                    break;

                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Log.d("sfdsf", "onResp: 用户取消");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Log.d("sfdsf", "onResp: 发送请求被拒绝");
                    finish();
                    break;
            }
        }
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\wxapi\WXEntryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */