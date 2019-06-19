package com.limei.movieapp.huiying.my;

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

import com.limei.movieapp.huiying.R;
import com.limei.movieapp.huiying.unit.HttpOk;
import com.limei.movieapp.huiying.unit.StatusBarUtil;
import com.limei.movieapp.huiying.unit.Unit;
import com.limei.movieapp.huiying.unit.WebAdds;

import org.json.JSONException;
import org.json.JSONObject;

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


public class MyNiChengActivity
        extends AppCompatActivity {
    @Bind({R.id.fanhui})
    ImageView fanhui;
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message paramAnonymousMessage) {
            if (paramAnonymousMessage.arg1 == 0) {
                Intent intent = new Intent();
                intent.putExtra("name", MyNiChengActivity.this.name.getText().toString().trim());
                MyNiChengActivity.this.setResult(2, intent);
                MyNiChengActivity.this.finish();
            } else {
                int i = paramAnonymousMessage.arg1;
            }
            return false;
        }
    });
    @Bind({R.id.name})
    EditText name;
    @Bind({R.id.rightss})
    TextView rightss;
    private String[] strs;
    @Bind({R.id.title})
    TextView title;

    private void getData(String paramString) {
        FormBody formBody = new FormBody.Builder().add("id", this.strs[2]).add("name", paramString).build();
        HttpOk.GetData(WebAdds.HTTPMXIUGAINICHENG, formBody, new Callback() {
            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException) {
                Log.d("kananan", "onFailure: 错误");
            }

            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
                    throws IOException {
                String s = paramAnonymousResponse.body().string();

                try {
                    if (new JSONObject(s).getString("code").equals("000")) {
                        Message message = new Message();
                        message.arg1 = 0;
                        handler.sendMessage(message);
                        return;
                    }
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_my_ni_cheng);
        ButterKnife.bind(this);
        this.strs = Unit.load(this).split(",");
        this.title.setText("昵称");
        this.rightss.setText("完成");
    }

    @OnClick({R.id.fanhui, R.id.rightss})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.fanhui) {
            if (i != R.id.rightss) {
                return;
            }
            getData(this.name.getText().toString().trim());
            return;
        }
        finish();
    }
}


/* Location:              G:\huiyingAPK\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\limei\movieapp\huiying\my\MyNiChengActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
