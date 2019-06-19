/**
 * 添加
 */
package com.limei.movieapp.huiying.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.info.MyXiaoXiXiangInfo;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.WebAdds;
import com.zzhoujay.richtext.RichText;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

public class MyXiaoXiXiangActivity
        extends AppCompatActivity {
    @Bind({R.id.biaoti})
    TextView biaoti;
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {

                try {
                    MyXiaoXiXiangActivity.this.biaoti.setText(MyXiaoXiXiangActivity.this.myXiaoXiXiangInfo.getData().getTitle());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(MyXiaoXiXiangActivity.this.myXiaoXiXiangInfo.getData().getContent());
                    stringBuilder.append("");
                    if (!stringBuilder.toString().equals("null")) {
                        String newpic = MyXiaoXiXiangActivity.this.myXiaoXiXiangInfo.getData().getContent().replaceAll("src=\\\"", "src=\\\"http://hy.shiduweb.com");
                        Log.i("sds", newpic);
                        RichText.from(newpic).into(MyXiaoXiXiangActivity.this.xiangqing);
                    }
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(MyXiaoXiXiangActivity.this.myXiaoXiXiangInfo.getData().getCtime());
                    stringBuilder1.append("000");
                    Date date = new Date(Long.parseLong(stringBuilder1.toString()));
                    String sa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    MyXiaoXiXiangActivity.this.timess.setText(sa);

                } catch (Exception e) {
                }
            }
            if (paramAnonymousMessage.arg1 == 1) {
                Toast.makeText(MyXiaoXiXiangActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            }

            return false;
        }
    });
    @Bind({R.id.head})
    RelativeLayout head;
    private String id;
    private MyXiaoXiXiangInfo myXiaoXiXiangInfo;
    @Bind({R.id.timess})
    TextView timess;
    @Bind({R.id.xiangqing})
    TextView xiangqing;

    private void getData() {
        FormBody localFormBody = new FormBody.Builder().add("id", this.id).build();
        HttpOk.GetData(WebAdds.HTTPXIAOXIXIANGQING, localFormBody, new okhttp3.Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                Gson gson = new Gson();
                myXiaoXiXiangInfo = gson.fromJson(s, MyXiaoXiXiangInfo.class);
                if (MyXiaoXiXiangActivity.this.myXiaoXiXiangInfo.getCode().equals("000")) {
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
        StatusBarUtil.StatusBarLightMode(this, 2);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_xiao_xi_xiang);
        ButterKnife.bind(this);
        this.id = getIntent().getStringExtra("id");
        getData();
    }

    @OnClick({R.id.fanhui, R.id.biaoti})
    public void onViewClicked(View paramView) {
        if (paramView.getId() != R.id.fanhui) {
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyXiaoXiXiangActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */