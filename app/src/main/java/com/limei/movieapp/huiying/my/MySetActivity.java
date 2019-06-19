package com.limei.movieapp.huiying.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.limei.movieapp.huiying.MainActivity;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.AlertDialog;
import com.limei.movieapp.huiying.unit.QingliHuancun;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 有不同
 */


public class MySetActivity
        extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editoryunxing;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            MySetActivity.this.progressDialog.dismiss();
            MySetActivity.this.huancun.setText("0MB");
            return false;
        }
    });
    @Bind({R.id.huancun})
    TextView huancun;
    private String newmes;
    @Bind({R.id.newtong})
    Switch newtong;
    private ProgressDialog progressDialog;
    @Bind({R.id.qinglihuan})
    RelativeLayout qinglihuan;
    @Bind({R.id.rightss})
    TextView rightss;
    @Bind({R.id.startgoupiao})
    TextView startgoupiao;
    @Bind({R.id.title})
    TextView title;
    private SharedPreferences userSettings;
    private SharedPreferences userSettingsyunxing;
    @Bind({R.id.xiazai})
    Switch xiazai;
    private String yunxingshang;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_set);
        ButterKnife.bind(this);
        this.huancun.setText(QingliHuancun.getTotalCacheSize(this));
        this.title.setText("设置");
        this.userSettings = getSharedPreferences("huiyingnewme", 0);
        this.newmes = this.userSettings.getString("huiyingnewme", "10000");
        this.editor = this.userSettings.edit();
        this.userSettingsyunxing = getSharedPreferences("huiyingxiazai", 0);
        this.yunxingshang = this.userSettingsyunxing.getString("huiyingxiazai", "10000");
        this.editoryunxing = this.userSettingsyunxing.edit();
        if (this.newmes.equals("1")) {
            this.newtong.setChecked(true);
        } else if (this.newmes.equals("0")) {
            this.newtong.setChecked(false);
        }
        if (this.yunxingshang.equals("1")) {
            this.xiazai.setChecked(true);
        } else if (this.yunxingshang.equals("0")) {
            this.xiazai.setChecked(false);
        }
        this.newtong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean == true) {
                    MySetActivity.this.editor.putString("huiyingnewme", "1");
                } else {
                    MySetActivity.this.editor.putString("huiyingnewme", "0");
                }
                MySetActivity.this.editor.commit();
            }
        });
        this.xiazai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean == true) {
                    MySetActivity.this.editoryunxing.putString("huiyingxiazai", "1");
                } else {
                    MySetActivity.this.editoryunxing.putString("huiyingxiazai", "0");
                }
                MySetActivity.this.editoryunxing.commit();
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.qinglihuan, R.id.startgoupiao})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.qinglihuan) {
                if (i != R.id.startgoupiao) {
                    return;
                }
                new AlertDialog(this).builder().setMsg("提示").settitlContent("是否退出此账号！").setPositiveButton("确定", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        Unit.save("", MySetActivity.this);
                        Unit.destoryActivity("main");
                        MySetActivity.this.startActivity(new Intent(MySetActivity.this, MainActivity.class));
                        MySetActivity.this.finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                    }
                }).show();
                return;
            }
            new AlertDialog(this).builder().setMsg("确定清理缓存吗？").settitlV().setPositiveButton("确定", new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    progressDialog = ProgressDialog.show(MySetActivity.this, "缓存清理中……", "请稍后……", true, false);
                    QingliHuancun.clearAllCache(MySetActivity.this);
                    new Thread(new Runnable() {
                        public void run() {
                            int i = 0;
                            while (i < 5) {
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException localInterruptedException) {
                                    localInterruptedException.printStackTrace();
                                }
                                i += 1;
                            }
                            Message localMessage = new Message();
                            localMessage.arg1 = 0;
                            MySetActivity.this.handler.sendMessage(localMessage);
                        }
                    }).start();
                }
            }).setNegativeButton("取消", new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                }
            }).show();
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MySetActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */