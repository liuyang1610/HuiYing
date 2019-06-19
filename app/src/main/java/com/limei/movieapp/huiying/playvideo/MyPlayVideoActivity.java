package com.limei.movieapp.huiying.playvideo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.application.MyApplication;
import com.limei.movieapp.huiying.info.CustomMission;
import com.limei.movieapp.huiying.info.MyZhengZaiXiangInfo;
import com.limei.movieapp.huiying.my.MySetActivity;
import com.limei.movieapp.huiying.unit.AlertDialog;
import com.limei.movieapp.huiying.unit.BaseActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.view.MyJzvdStd;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JzvdStd;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Status;


/**
 * 有不同
 */


public class MyPlayVideoActivity
        extends BaseActivity
        implements View.OnClickListener {
    private String Did;
    private final int THUMB_SIZE = 150;
    private Dialog bottomDialog;
    private View contentView;
    private CustomMission customMission;
    private Status currentStatus = new Status();
    private String days;
    private Disposable disposable;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {

            if (paramAnonymousMessage.arg1 == 0) {
                MyPlayVideoActivity.this.videoplayer = ((MyJzvdStd) MyPlayVideoActivity.this.findViewById(R.id.videoplayer));

                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(WebAdds.YUMING);
                stringBuilder3.append(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getMovieaddress());
                MyPlayVideoActivity.this.videoplayer.setUp(stringBuilder3.toString(), "", 0);

                RequestOptions requestOptions = new RequestOptions().centerCrop();
                Object localObject2 = new StringBuilder();
                ((StringBuilder) localObject2).append(WebAdds.YUMING);
                ((StringBuilder) localObject2).append(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getPic1());
                Glide.with(MyPlayVideoActivity.this).load(((StringBuilder) localObject2).toString()).apply(requestOptions).into(MyPlayVideoActivity.this.videoplayer.thumbImageView);

                if (MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getIscache().equals("1")) {
                    MyPlayVideoActivity.this.ishuancun.setText("已缓存");
                }
                MyPlayVideoActivity.this.piaopiao.setText(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getNum());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getDtime());
                stringBuilder.append("000");
                long l = (Long.parseLong(stringBuilder.toString()) - Unit.getTime()) / 1000L / 60L / 60L % 60L / 24L;

                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("还剩");
                stringBuilder1.append(MyPlayVideoActivity.this.days);
                stringBuilder1.append("");
                MyPlayVideoActivity.this.shengyuday.setText(stringBuilder1.toString());

                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(WebAdds.YUMING);
                stringBuilder2.append(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getMovieaddress());


                localObject2 = new StringBuilder();
                ((StringBuilder) localObject2).append(WebAdds.YUMING);
                ((StringBuilder) localObject2).append(MyPlayVideoActivity.this.myZhengZaiXiangInfo.getData().getPic1());
                ((StringBuilder) localObject2).append("￥");
                ((StringBuilder) localObject2).append(MyPlayVideoActivity.this.name);
                ((StringBuilder) localObject2).append("￥");
                ((StringBuilder) localObject2).append(MyPlayVideoActivity.this.ticketnumber);
                localObject2 = ((StringBuilder) localObject2).toString();

                customMission = new CustomMission(WebAdds.YUMING + myZhengZaiXiangInfo.getData().getMovieaddress(),
                        name, (String) localObject2);

                RxDownload.INSTANCE.create(customMission).subscribe();


            }
            if (paramAnonymousMessage.arg1 == 1) {

                if (MyPlayVideoActivity.isWifi(MyPlayVideoActivity.this)) {
                    RxDownload.INSTANCE.start(customMission).subscribe();
                    Toast.makeText(MyPlayVideoActivity.this, "已添加至缓存列表", Toast.LENGTH_SHORT).show();
                    return false;
                }
                new AlertDialog(MyPlayVideoActivity.this).builder().setMsg("提示").settitlContent("当前为移动网络，是否继续观看！").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {

                        RxDownload.INSTANCE.start(customMission).subscribe();
                        Toast.makeText(MyPlayVideoActivity.this, "已添加至缓存列表", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {
                    }
                }).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 2) {
                if (MyPlayVideoActivity.isWifi(MyPlayVideoActivity.this)) {
                    MyPlayVideoActivity.this.play.setVisibility(View.GONE);
                    MyPlayVideoActivity.this.videoplayer.startVideo();
                    return false;
                }
                new AlertDialog(MyPlayVideoActivity.this).builder().setMsg("提示").settitlContent("当前为移动网络，是否继续观看！").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {

                        MyPlayVideoActivity.this.play.setVisibility(View.GONE);
                        MyPlayVideoActivity.this.videoplayer.startVideo();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {
                    }
                }).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 3) {
                return false;
            }
            if (paramAnonymousMessage.arg1 == 4) {
                Toast.makeText(MyPlayVideoActivity.this, "已经播放完成", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 5) {
                return false;
            }
            if (paramAnonymousMessage.arg1 == 6) {
                if (MyPlayVideoActivity.this.iswe == true) {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(WebAdds.YUMING);
                    stringBuilder.append(MyPlayVideoActivity.this.url);
                    WeiFri(stringBuilder.toString());
                    return false;
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(WebAdds.YUMING);
                stringBuilder.append(MyPlayVideoActivity.this.url);
                WeiPeng(stringBuilder.toString());
                return false;
            }
            if (paramAnonymousMessage.arg1 == 7) {
                Toast.makeText(MyPlayVideoActivity.this, "票数不够", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 8) {
                if (MyPlayVideoActivity.isWifi(MyPlayVideoActivity.this)) {
                    RxDownload.INSTANCE.start(MyPlayVideoActivity.this.customMission).subscribe();
                    Toast.makeText(MyPlayVideoActivity.this, "已添加至缓存列表", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (MyPlayVideoActivity.this.isxiazai == true) {
                    return false;
                }
                new AlertDialog(MyPlayVideoActivity.this).builder().setMsg("提示").settitlContent("当前为移动网络，继续下载,请前往设置").setPositiveButton("前往设置", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {
                        MyPlayVideoActivity.this.startActivity(new Intent(MyPlayVideoActivity.this, MySetActivity.class));
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {
                    }
                }).show();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 9) {
                Toast.makeText(MyPlayVideoActivity.this, "票数不够", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    @Bind({R.id.huancun})
    RelativeLayout huancun;
    private String id;
    @Bind({R.id.ishuancun})
    TextView ishuancun;
    private boolean iswe;
    private boolean isxiazai;
    private MyZhengZaiXiangInfo myZhengZaiXiangInfo;
    private String name;
    @Bind({R.id.piaopiao})
    TextView piaopiao;
    @Bind({R.id.play})
    ImageView play;
    @Bind({R.id.shengyuday})
    TextView shengyuday;
    @Bind({R.id.songpiao})
    RelativeLayout songpiao;
    private String[] strs;
    private String ticketnumber;
    @Bind({R.id.titless})
    RelativeLayout titless;
    private String uid;
    private String url;
    private SharedPreferences userSettingsyunxing;
    MyJzvdStd videoplayer;
    private IWXAPI wxApi;
    private String yunxingshang;
    @Bind({R.id.zhaungai})
    TextView zhaungai;

    private void WeiFri(String paramString) {
        MyApplication.id = this.id;
        MyApplication.uid = this.uid;
        MyApplication.ticketnumber = this.ticketnumber;

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = paramString;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "送你一张电影票";
        msg.description = "快来看大片吧";
        try {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
            msg.setThumbImage(thumbBmp);
            bmp.recycle();
        } catch (Exception e) {

        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = 0 == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        MyApplication.mWxApi.sendReq(req);

    }

    private String buildTransaction(String paramString) {
        if (paramString == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(System.currentTimeMillis());
        return localStringBuilder.toString();
    }

    public static String formatDuring(long paramLong) {
        long l1 = paramLong / 86400000L;
        long l2 = paramLong % 86400000L / 3600000L;
        long l3 = paramLong % 3600000L / 60000L;
        long l4 = paramLong % 60000L / 1000L;
        paramLong /= 1000L;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(l1);
        localStringBuilder.append(" days ");
        localStringBuilder.append(l2);
        localStringBuilder.append(" hours ");
        localStringBuilder.append(l3);
        localStringBuilder.append(" minutes ");
        localStringBuilder.append(l4);
        localStringBuilder.append(" seconds ");
        return localStringBuilder.toString();
    }

    private void getDataDown(String paramString) {
        FormBody formBody = new FormBody.Builder().add("ticketnumber", paramString).build();
        HttpOk.GetData(WebAdds.HTTPBOFANGQIAN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg1 = 1;
                        handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg1 = 3;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataLoadJinDu(String paramString) {
        Log.i("jindu", paramString);
        FormBody formBody = new FormBody.Builder().add("ticketnumber", this.ticketnumber).add("playtime", paramString).build();
        HttpOk.GetData(WebAdds.HTTPSHYANGCHUANJINDU, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    new JSONObject(s).getString("code").equals("000");
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataSongPiao() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).add("ticketnumber", this.ticketnumber).add("did", this.Did).build();
        HttpOk.GetData(WebAdds.HTTPFENXIANG, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("asdsadsayhjy", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {

                        url = jsonObject.getJSONObject("data").getString("url");
                        id = jsonObject.getJSONObject("data").getString("id");
                        uid = jsonObject.getJSONObject("data").getString("uid");
                        ticketnumber = jsonObject.getJSONObject("data").getString("ticketnumber");

                        Message message = new Message();
                        message.arg1 = 6;
                        handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg1 = 7;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataWan(String paramString) {
        FormBody formBody = new FormBody.Builder().add("ticketnumber", paramString).build();
        HttpOk.GetData(WebAdds.HTTPBOFANGWAN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg1 = 4;
                        handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg1 = 5;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataXiaoHao(String paramString) {
        FormBody formBody = new FormBody.Builder().add("ticketnumber", paramString).build();
        HttpOk.GetData(WebAdds.HTTPBOFANGQIAN, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
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
                        handler.sendMessage(message);
                        return;
                    }
                    if (jsonObject.getString("code").equals("002")) {
                        Message message = new Message();
                        message.arg1 = 3;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDataZhengZaiXiang() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).add("ticketnumber", this.ticketnumber).add("did", this.Did).build();
        HttpOk.GetData(WebAdds.HTTPZHENGZAIXIANGQING, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("hahahah", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();
                Log.d("hahahah", "onFailure: " + s);
                Gson gson = new Gson();
                try {
                    myZhengZaiXiangInfo = gson.fromJson(s, MyZhengZaiXiangInfo.class);

                    if (MyPlayVideoActivity.this.myZhengZaiXiangInfo.getCode().equals("000")) {
                        Message message = new Message();
                        message.arg1 = 0;
                        MyPlayVideoActivity.this.handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg1 = 1;
                    MyPlayVideoActivity.this.handler.sendMessage(message);
                    return;
                } catch (Exception e) {
                }
            }
        });
    }

    private static boolean isWifi(Context paramContext) {
//        ConnectivityManager = ((ConnectivityManager) paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
//        return (paramContext != null) && (paramContext.getType() == 1);
        return true;
    }

    private void show1() {
        this.bottomDialog = new Dialog(this, R.style.BottomDialog);
        this.contentView = LayoutInflater.from(this).inflate(R.layout.diglog_fenxiang, null);
        Object localObject = (ImageButton) this.contentView.findViewById(R.id.weichatfri);
        ImageButton localImageButton = (ImageButton) this.contentView.findViewById(R.id.weichatrpengyou);
        TextView localTextView = (TextView) this.contentView.findViewById(R.id.quxiao);
        ((ImageButton) localObject).setOnClickListener(this);
        localImageButton.setOnClickListener(this);
        localTextView.setOnClickListener(this);
        this.bottomDialog.setContentView(this.contentView);
        localObject = this.contentView.getLayoutParams();
        ((ViewGroup.LayoutParams) localObject).width = getResources().getDisplayMetrics().widthPixels;
        this.contentView.setLayoutParams((ViewGroup.LayoutParams) localObject);
        this.bottomDialog.getWindow().setGravity(80);
        this.bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        this.bottomDialog.setCanceledOnTouchOutside(true);
        this.bottomDialog.show();
    }

    public void WeiPeng(String paramString) {
        MyApplication.id = this.id;
        MyApplication.uid = this.uid;
        MyApplication.ticketnumber = this.ticketnumber;
        WXWebpageObject webpage1 = new WXWebpageObject();
        webpage1.webpageUrl = paramString;
        WXMediaMessage msg1 = new WXMediaMessage(webpage1);
        msg1.title = "中服免稅";
        msg1.description = "我正在看一件商品，快來看看吧！";
        try {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
            msg1.setThumbImage(thumbBmp);
            bmp.recycle();
        } catch (Exception e) {

        }
        SendMessageToWX.Req req1 = new SendMessageToWX.Req();
        req1.transaction = buildTransaction("webpage");
        req1.message = msg1;
        req1.scene = 1 == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        MyApplication.mWxApi.sendReq(req1);
    }

    public void onBackPressed() {
        if (JzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    public void onClick(View paramView) {
        int i = paramView.getId();
        if (i != R.id.quxiao) {
            switch (i) {
                default:
                    return;
                case R.id.weichatrpengyou:
                    this.iswe = false;
                    getDataSongPiao();
                    return;
                case R.id.weichatfri:
                    this.iswe = true;
                    getDataSongPiao();
                    return;
            }

        }
        this.bottomDialog.dismiss();
    }

    @RequiresApi(api = 21)
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_my_play_video);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        this.wxApi = MyApplication.mWxApi;
        this.strs = Unit.load(this).split(",");
        this.Did = getIntent().getStringExtra("Did");
        this.ticketnumber = getIntent().getStringExtra("ticketnumber");
        this.name = getIntent().getStringExtra("name");
        this.days = getIntent().getStringExtra("days");
        getDataZhengZaiXiang();

    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    protected void onResume() {
        super.onResume();
        this.userSettingsyunxing = getSharedPreferences("huiyingxiazai", 0);
        this.yunxingshang = this.userSettingsyunxing.getString("huiyingxiazai", "10000");
        if (this.yunxingshang.equals("1")) {
            this.isxiazai = true;
            return;
        }
        if (this.yunxingshang.equals("0")) {
            this.isxiazai = false;
        }
    }

    @OnClick({R.id.huancun, R.id.songpiao, R.id.fanhui, R.id.play})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.huancun) {
                if (i != R.id.play) {
                    if (i != R.id.songpiao) {
                        return;
                    }
                    show1();
                    return;
                }
                if (this.myZhengZaiXiangInfo.getData().getIsuse().equals("1")) {
                    this.play.setVisibility(View.GONE);
                    this.videoplayer.startVideo();
                    return;
                }
                new AlertDialog(this).builder().setMsg("提示").settitlContent("观看需要消耗一张电影票，是否继续观看").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        MyPlayVideoActivity.this.getDataXiaoHao(MyPlayVideoActivity.this.ticketnumber);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                    }
                }).show();
                return;
            }
            if (this.zhaungai.getText().toString().trim().equals("缓存")) {
                if (this.myZhengZaiXiangInfo.getData().getIsuse().equals("1")) {
                    this.play.setVisibility(View.GONE);
                    RxDownload.INSTANCE.start(customMission).subscribe();
                    Toast.makeText(this, "已添加至缓存列表", Toast.LENGTH_SHORT).show();

                    return;
                }
                new AlertDialog(this).builder().setMsg("提示").settitlContent("缓存需要消耗一张电影票，是否继续缓存").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {

                        MyPlayVideoActivity.this.getDataDown(MyPlayVideoActivity.this.ticketnumber);

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                    }
                }).show();
            }
        } else {
            long l1 = this.videoplayer.getCurrentPositionWhenPlaying();
            long l2 = l1 / 1000L;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(l2);
            stringBuilder.append("");
            getDataLoadJinDu(stringBuilder.toString());
            finish();
        }
    }

}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\playvideo\MyPlayVideoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */



