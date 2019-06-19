package com.limei.movieapp.huiying.zhifu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.ZhiFuSelerInfo;
import com.limei.movieapp.huiying.qianbaomingxi.MyQianBaoMingActivity;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.limei.movieapp.huiying.zhifubao.OrderInfoUtil2_0;
import com.limei.movieapp.huiying.zhifubao.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

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


public class MyChongZhiActivity
        extends AppCompatActivity {
    public static final String APPID = "2018090761294650";
    public static final String RSA2_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCkHT1VQYwnNwtpcO6CfktRP8MyYlAkTyhg+UY4/RxVxTdkYIYzWNR7Oh5ckT93Id1ahk35879yR/7UHwByOsQYb5xKdJRheOWGXzPbkl+f0HfSfRhn0jInOVSBT4TIa+iRj+FBsy5i7dPw8e9V6sdIotYeLIbz3MbhUUbNVvFK1osRlg9KZL1z9bIfyPtrkj+n20PdjN1hGI2muidcnTcHxjh6acOQw2Ux+i331NuU17+L8iOcUh76XrKNcf/+4V+jBcyCbIVP78A3qmAhvYV50/jhJh6C7eLcUFbZj1dUUDTvlVPnn1WaQu0NtOHnvjdqtK/tRirdwTcVcVxKrcRLAgMBAAECggEBAJItUSINPk73onyKkIc/Assd5jmMhCGzOB4YKko8eO6UuV/48c/ugWQ5Qi3lWgg867LkCbRnPdEG+53o9zbHzT6Ou0E+UaP4UUi8p64d1Hq+7ttXfVOMUAvL7s83CuiR5RcydstUVC7d83ebpzh55IN4NwutUY8IyUSMZmoj1Mx4mz//OpX9AUPRSX8NN2bhEkpcK8n2B+Pnh9RmqBCtWwSZmJFa9nC4Tn8Tu5Aa6RyDo0DbH4Kks3jHUXaG2VdoUrhU3Lk2O3N0DW1uSpvkA43n2urpRJuXG0f6EDCHoG2LEHSASqVyNWyPlxkMN820Wk3e8tb7OzxvERtWugH3qUECgYEAz0QFJnbaIdKDvd+bycReKXtK2daldB/GNMuuW1mqqpcedBRvgu+a1vSKcoGqKdcKZCUMK5f5jeJA9cDacjEv+2QdUe6oUlO+oZA6AQRziwwNE+Q2vENiT516wz2pJZIqJdAud7+akrR6/8IrO4LO1zGRUept9xiUBt9hNZESzjECgYEAyrPLb505aLYSeceCqCh4A1qcKG2I17NChnubPHw+moAN+Im2eImWX1FUARYzbMb/7EwTKhDDysZXJI+mR/MsSmmFBP4TaorNXOv+mjQ7M5TIxtIzqxi8Ezx+y5nChvR84ixM+vzVx6XqJyncJYNcb07yOpOTMNnW+JwfnhHBbzsCgYBTPf1XimpnJRY2HuWBrnUm8daJENUYg15me9tF4whRRSo88qwqiTLF3CbygDMoZK5OtDZkF8BphJIUYWUacbjUnUvNSS2fCvVIDBXPrMr+8FMcRw7Yo7OLSDMy0MPrS/3Te/39qB1biFLR/1i/qPSLpuO3Y5DQqbVh4Ts4eVB1MQKBgQCLKS50fStJsR40Dv2rdsUWAGIO+0wkUgRNEZU8zEuqhFmTFPB6BnN/EvWqHVTGzEQtNPk/Ou1ATDTNe/V7lf4FRDY3fDtWPN7KeZZ8xXkTa4T/wpYIU93jEvg1zWUYg8cs+58J/QTDoSuFIZ7q/ik6nad9AY3ezt9gJMcsHSKAuwKBgQCFKyY+4CqzpGcaUo8m3kY9ETgDVqsae8yWgVxsb1WOecI/IKcbN2ME7oYMMHZ7KfQLP6/hlNWNyFTq6m9o6v79INEP2bZToSU11TGQHSiaUmFkR1saK4+Bp87yrUS9DcUryCZWWQsTK9fOk/6Ovq8A7c2+E58cFN1eESQCXgJgHw==";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 3;

    @Bind({R.id.a})
    TextView a;
    private MyXuanZeAdapter adapter;
    private String discount = "";
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                manager = new GridLayoutManager(MyChongZhiActivity.this, 3);
                adapter = new MyXuanZeAdapter(MyChongZhiActivity.this, MyChongZhiActivity.this.zhiFuSelerInfo);

                MyChongZhiActivity.this.xuanzeshow.setLayoutManager(MyChongZhiActivity.this.manager);
                MyChongZhiActivity.this.xuanzeshow.setAdapter(MyChongZhiActivity.this.adapter);
                MyChongZhiActivity.this.adapter.setOnItemClickListener(new MyXuanZeAdapter.OnItemClickListener() {
                    public void onItemClick(View paramAnonymous2View, int paramAnonymous2Int) {
                        MyChongZhiActivity.this.jine.setText(((ZhiFuSelerInfo.DataEntity.ListEntity) MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().get(paramAnonymous2Int)).getFull());
                        int i = 0;
                        while (i < MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().size()) {
                            ((ZhiFuSelerInfo.DataEntity.ListEntity) MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().get(i)).setXuan(false);
                            i += 1;
                        }
                        ((ZhiFuSelerInfo.DataEntity.ListEntity) MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().get(paramAnonymous2Int)).setXuan(true);
                        MyChongZhiActivity.this.adapter.notifyDataSetChanged();
                        //MyChongZhiActivity.access$302(MyChongZhiActivity.this, ((ZhiFuSelerInfo.DataEntity.ListEntity) MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().get(paramAnonymous2Int)).getReduce());
                    }
                });
                MyChongZhiActivity.this.num.setText(MyChongZhiActivity.this.zhiFuSelerInfo.getData().getBalance());
                return false;
            }
            if (paramAnonymousMessage.arg1 == 1) {
                return false;
            }
            if (paramAnonymousMessage.arg1 == 2) {
                Toast.makeText(MyChongZhiActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                MyChongZhiActivity.this.getData();
                return false;
            }
            if (paramAnonymousMessage.arg1 == 3) {
                PayResult payResult = new PayResult((Map) paramAnonymousMessage.obj);
                String resultInfo = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
                Log.i("datas", resultStatus);
                if (TextUtils.equals(resultStatus, "9000")) {
                    Log.i("datas", "支付成功");
                    MyChongZhiActivity.this.getDataZhiFu();
                    return false;
                }
                Toast.makeText(MyChongZhiActivity.this, "支付失败,已加入订单", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
    @Bind({R.id.jine})
    EditText jine;
    @Bind({R.id.lijizhifu})
    TextView lijizhifu;
    private GridLayoutManager manager;
    private String mode = "4";
    @Bind({R.id.num})
    TextView num;
    private String price = "";
    @Bind({R.id.rightss})
    TextView rightss;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;
    @Bind({R.id.weixin})
    RelativeLayout weixin;
    @Bind({R.id.xuanwei})
    ImageView xuanwei;
    @Bind({R.id.xuanzeshow})
    RecyclerView xuanzeshow;
    @Bind({R.id.xuanzhi})
    ImageView xuanzhi;
    private ZhiFuSelerInfo zhiFuSelerInfo;
    @Bind({R.id.zhifubao})
    RelativeLayout zhifubao;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.strs[2]).build();
        HttpOk.GetData(WebAdds.HTTPWODEZHANGHU, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                zhiFuSelerInfo = gson.fromJson(s, ZhiFuSelerInfo.class);

                if (MyChongZhiActivity.this.zhiFuSelerInfo.getCode().equals("000")) {
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

    private void getDataZhiFu() {
        FormBody localFormBody = new FormBody.Builder().add("uid", this.strs[2]).add("mode", this.mode).add("price", this.price).add("discount", this.discount).build();
        HttpOk.GetData(WebAdds.HTTPCHONGZHI, localFormBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("datas", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    if (new JSONObject(s).getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg1 = 2;
                        handler.sendMessage(message);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_chong_zhi);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.title.setText("我的账户");
        this.rightss.setText("充值明细");
        this.jine.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean) {
                    Log.i("sfdf", "sadfsf111111");
                    int i = 0;
                    while (i < MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().size()) {
                        zhiFuSelerInfo.getData().getList().get(i).setXuan(false);
                        i += 1;
                    }
                    MyChongZhiActivity.this.adapter.notifyDataSetChanged();
                    //MyChongZhiActivity.access$302(MyChongZhiActivity.this, "0");
                    return;
                }
                Log.i("sfdf", "sadfsf22222222");
            }
        });
        this.jine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                int i = 0;
                while (i < MyChongZhiActivity.this.zhiFuSelerInfo.getData().getList().size()) {
                    zhiFuSelerInfo.getData().getList().get(i).setXuan(false);
                    i += 1;
                }
                MyChongZhiActivity.this.adapter.notifyDataSetChanged();
                //MyChongZhiActivity.access$302(MyChongZhiActivity.this, "0");
            }
        });
        getData();
    }

    @OnClick({R.id.fanhui, R.id.rightss, R.id.lijizhifu, R.id.weixin, R.id.zhifubao, R.id.xuanwei, R.id.xuanzhi})
    public void onViewClicked(View paramView) {
        switch (paramView.getId()) {
            default:

            case R.id.zhifubao:
                this.mode = "2";
                this.xuanwei.setBackgroundResource(R.drawable.xuanzhong1);
                this.xuanzhi.setBackgroundResource(R.drawable.xuanzhong);
                return;
            case R.id.xuanzhi:
                this.mode = "2";
                this.xuanwei.setBackgroundResource(R.drawable.xuanzhong1);
                this.xuanzhi.setBackgroundResource(R.drawable.xuanzhong);
                return;
            case R.id.xuanwei:
                this.mode = "1";
                this.xuanwei.setBackgroundResource(R.drawable.xuanzhong);
                this.xuanzhi.setBackgroundResource(R.drawable.xuanzhong1);
                return;
            case R.id.weixin:
                this.mode = "1";
                this.xuanwei.setBackgroundResource(R.drawable.xuanzhong);
                this.xuanzhi.setBackgroundResource(R.drawable.xuanzhong1);
                return;
            case R.id.rightss:
                startActivityForResult(new Intent(this, MyQianBaoMingActivity.class), 1);
                return;
            case R.id.lijizhifu:
                this.price = this.jine.getText().toString().trim();
                if (this.mode.equals("4")) {
                    Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((this.price.equals("")) && (!this.price.equals("0"))) {
                    Toast.makeText(this, "请填写充值金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (this.mode.equals("1")) {

                    return;
                }
                if (this.mode.equals("2")) {
                    zhifubao(this.jine.getText().toString().trim());
                    return;
                }
                break;
            case R.id.fanhui:
                finish();
        }
    }

    public void zhifubao(String jine) {

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, jine);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Log.i("fdsfds", "111等待支付kaishi");
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(MyChongZhiActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Log.i("fdsfds", "111支付kaishi");
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\zhifu\MyChongZhiActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
